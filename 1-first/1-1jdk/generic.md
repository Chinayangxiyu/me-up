# 1、 ThreadLocal

## 1-1、简述
线程本地变量，每个线程都有一个ThreadLocalMap来维护当前线程的一些变量。
通过set()、get()、remove()方法来维护ThreadLocalMap中的值。

## 1-2 ThreadLocalMap
（1）ThreadLocalMap内部维护的是Entry数组，Entry继承了WeakReference，并在构造器  
中包装ThreadLocal对象为弱引用，可以直接调用Reference的方法。当调用Entry.get()方法  
时，实际调用Reference的get()方法，如果存在返回引用地址，否则返回null。
```
  static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k); // 包装threadLocal对象为弱引用
                value = v;
            }
        }
```

（2）ThreadLocalMap处理哈希冲突：通过计算ThreadLocal对象的哈希值判断对应的数组索引，当出现哈希冲突时  
**使用开放寻址方法，往后遍历元素，找到空的位置保存Entry**，如果没找到空的位置，需要扩容。
```
private void set(ThreadLocal<?> key, Object value) {

            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);

            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();

                if (k == key) {
                    e.value = value;
                    return;
                }

                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }

            tab[i] = new Entry(key, value);
            int sz = ++size;
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();
        }
```

（3）get()方法，获取元素时先计算ThreadLocal的哈希值找到Entry元素，然后调用Reference.get()  
方法获取包装的ThreadLocal对象引用地址，比较引用地址是否相同，相同则返回，否则往后遍历找到元素。


**总结1：ThreadLocalMap使用开放寻址解决哈希冲突，这种方法只适合少量元素、哈希冲突较少的情况，  
如果元素、哈希冲突过多，那么遍历数组会很消耗性能。而HashMap使用链表处理哈希冲突更稳定。**

## 1-3、ThreadLocal内存泄漏
（1）ThreadLocalMap内部维护一个Entry\[\]数组保存元素，  
（2）Entry继承了WeakReference，并通过构造器去包装传入的ThreadLocal对象，使得  
Entry引用的ThreadLocal对象是一个弱引用。

（3）此时的引用链路为 currentThread -> ThreadLocalMap -> Entry\[\] -> threadLocal和value对象。  
（4）如果此时发生了GC，因为Entry对threadLocal的引用关系被包装成了弱引用，如果此时threadLocal没有在  
其它地方被引用（强引用），那么这个threadLocal将会被回收。  
（5）如果threadLocal被回收了，但是（currentThread -> ThreadLocalMap -> Entry\[\] -> value）  
这个引用链一直存在，如果Thread不被回收，那么entry对象将会一直占用内存无法回收。  
（6）threadLocal被回收后，就无法找到历史的value。


**重点：使用时一般都创建一个static final修饰的ThreadLocal对象，所以ThreadLocal对象  
一直有一个强引用进行关联，虽然在Entry包装了了弱引用，但是因为static final保证了强引用，ThreadLocal  
一直不会被回收。但是如果我们在使用后不调用remove()方法，就导致使用过的已经无效的Entry对象和  
应用的生命周期绑定在一起了，这也是属于内存泄漏的情况。**