本节只包含util包下的常用容器
# List
## 1、ArrayList
&emsp;&emsp;  底层结构是数组,默认初始长度为10；允许元素为null;  
&emsp;&emsp;  扩容机制:添加元素elements.size的长度>size的0.5,则扩容长度为elements.size + size;否则size = size + 0.5 * size;  
&emsp;&emsp;  查询复杂度:根据索引查询 O(1);根据对象查O(n)   
    
##2、LinkedList
&emsp;&emsp;  实现了接口Deque,双端链表,默认add操作是添加在链表尾部,查询时需要遍历.  
&emsp;&emsp;  查询复杂度:O(n)  

## 3、Vector
&emsp;&emsp;  基于数组实现的集合，线程安全的；默认长度为10.允许元素为null;  
&emsp;&emsp;  扩容机制:配置了capacityIncrement参数，每次以该参数为单位扩容；否则每次扩容一倍；  
&emsp;&emsp;  查询复杂度:根据索引查询 O(1);根据对象查O(n)。  

## 4、Stack
&emsp;&emsp;  继承自Vector，所以是线程安全的；在此的基础上实现了栈的先进后出规则(FILO)；
&emsp;&emsp;  扩容机制：和Vector一致。



# Map
**&emsp;&emsp;   hashCode()方法保证相同的输入得到相同的输出，不保证不同的输入有不同的输出**  
**&emsp;&emsp;   equals()是判断两个对象是否相等**  
**&emsp;&emsp;  【补充】：HashMap的Entry元素，在产生哈希中途后，如果阈值超过8会转为红黑树，但是我们知道树的存储需要和节点的值**   
**进行大小比较的；本来就已经产生哈希冲突了，那么怎么继续获取一个值来构建R-B Tree呢；在HashMap中是调用了System.identityHashCode**  
**获取key的默认哈希值，根据这个默认哈希值构建B-R Tree。System.identityHashCode()是对象在未重写hashCode方法的时候系统自带的**  
**hashCode计算方法。**    
**&emsp;&emsp;  【小概率】所以在使用Map的时候如果不重写hashCode（）方法，使用Map的时候两次计算调用的都是System.identityHashCode()**  
**方法，如果产生哈希冲突；那么构建红黑树时，得出的哈希值是一致的，最后导致整个树的所有值都一样，查一个key可能会遍历完整个树。**  

##1、HashMap

&emsp;&emsp;    底层接口是数组 + (链表 || 红黑树);元素存链表还是红黑树由哈希冲突的长度实现，默认值是8；查询的适合根据hash值、key对象地址、
            key对象equals()方法判断是否为同一个对象。  
&emsp;&emsp;    扩容机制：当已添加的数组元素达到加载因子值（默认0.75），以2的N次幂进行扩容，因为（2的N次幂-1）的二进制每一位都是1，能够降低哈希  
            冲突，使得元素分布更均匀；HashMap在计算当前元素属于数组哪个索引的时候，会把插入元素的哈希值 和 数组长度减一做与运算（int n = hash &  
            length - 1），运算结果n就是索引。如果数组的长度为2的倍数扩容，那么 length - 1的最后几位永远是 ***1111，做&位运算能分布更均匀。 举例  
            如果数组长度为15， length -1 转为二进制 = 1110。如果有两个元素的哈希值为8、9。转为二进制分别是1000，1001。1000 & 1110 = 1000，1001   
            & 1110 = 1000，运算的结果一致，产生了哈希冲突。  
&emsp;&emsp;    查询复杂度：不存在哈希冲突，链表元素为1，复杂度是O(1)；最坏的情况如果Entry存的是链表，且查询的元素在链表尾部，复杂度为O(n)；最坏  
            的情况如果Entry存的是红黑树，复杂度为log(n)  
&emsp;&emsp;    jdk1.8的优化：扩容的时候需要将链表遍历拷贝到新的数组，遍历时从头节点开始的，1.8以前产生hash冲突，添加链表的时候采用头结点插入，  
            链表的顺序会颠倒一次，因为HashMap不是线程安全的，当产生并发的时候可能会出现死链。1.8优化后，使用尾节点插入，不会颠倒链表顺序，不会
            产生死链。  
&emsp;&emsp;    1.7死链描述：链表{A,B,C,D}，复制的时候采用头节点插入，先复制A节点，然后设置A的pre节点，此时线程并发，线程1获取到A的next是B，将B
            设置为A的pre节点；此时线程2获取节点B的next 已经指向了节点A；最后设置B节点的pre时会设置为A。最终，形成死链。  
&emsp;&emsp;    1.8使用红黑树+链表替换纯链表的优势：如果hashCode方法分布均匀，产生哈希冲突的概率极低，文档中给出的数据是(0.00000006)；当我们自己  
            实现的hashCode()方法分布不均，或者hash算法被人破解攻击，产生了大量哈希冲突时；使用存链表维护在链表长度过长时性能降低，使用红黑树可以  
            提升效率。       
    
HashMap put方法源码解析
``` 
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) // 判断tab是否初始化
            n = (tab = resize()).length; // 初始化tab
            // 根据key的hash值和tab长度进行哈希计算索引
        if ((p = tab[i = (n - 1) & hash]) == null) 
                // 该索引为空，则新建Node添加到数组
            tab[i] = newNode(hash, key, value, null);
            
            // 存在hash冲突，当前添加的key在数组已经存在
        else {
            Node<K,V> e; K k;
                // p表示已经存在的k，如果k和传进来的key比较相同，则会覆盖value值
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
                // p是TreeNode对象，调用红黑树的添加方法
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                
                // 否则说明p是链表，需要遍历链表处理
            else {
                for (int binCount = 0; ; ++binCount) {
                    // 链表的下一个元素为空，则可以将添加的元素加到链表尾部
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                            // 链表长度超过阈值，需要转为红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // p.next不为空，则将p.next和当前添加的key进行比较，如果相同，覆盖value值。
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
                // e!=null，说明当前tabley已经存在相同的key需要，执行value值的覆盖策略。
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                    // 参数onlyIfAbsent，表示存在key相同的时候，是否覆盖value值得策略。
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                    // 该方法是LinkedHashMap的回调方法，HashMap中为空方法
                afterNodeAccess(e);
                return oldValue;
            }
        }
        // 增加修改次数
        ++modCount;
        // 是否扩容
        if (++size > threshold)
            resize();
            // 该方法是LinkedHashMap的回调方法，HashMap中为空方法
        afterNodeInsertion(evict);
        return null;
    }

```


## 2、LinkedHashMap
https://www.jianshu.com/p/8f4f58b4b8ab
&emsp;&emsp;  HashMap的存储是无需的，LinkedHashMap额外添加了一个双向链表维护数组中每个Node的遍历顺序。LinkedHashMap中的Entry类  
&emsp;&emsp;  继承自HashMap的Node，Node 本身是一个单链表或红黑树用于处理hash冲突；Entry类多了before, after属性指定维护的双向链表的前后节点  
&emsp;&emsp;  查询复杂度：基于HashMap实现的所以，最好是O(1)，最坏的O(n)

**注意LinkedHashMap的accessOrder属性控制双向链表的顺序，默认值为false，代表链表的顺序为插入顺序；true表示,**  
**调用get()方法时，会将该节点移动到链表尾部，所以最后访问的节点遍历顺序也在最后。**  

## 3、TreeMap
[treeMap详解参考](https://www.cnblogs.com/LiaHon/p/11221634.html)  
[红黑树](https://www.cnblogs.com/LiaHon/p/11203229.html)
&emsp;&emsp;    底层使用红黑树（R-B tree）实现，添加的key使用默认或传入的comparator和树中的节点进行比较，小于0继续和左子树比较，  
            大于0和右子树比较，指导子叶节点为null,执行插入。fixAfterInsertion()调整数的平衡性。
&emsp;&emsp;    查询复杂度：log(n)，查询次数和数的高度相关。

## 4、HashTable
&emsp;&emsp;    HashTable使用简单的数组+ 链表实现，所有方法都加了synchronized锁，是线程安全的；但是加的锁对象是当前table，所以性能
            很低，不推荐使用。
&emsp;&emsp;    扩容机制：记载因子默认0.75。
&emsp;&emsp;    查询复杂度：无锁竞争，无哈希冲突；最好的情况是根据数组索引查询复杂度为O(1)，存在哈希冲突时需要遍历链表复杂度为O(n).           
    
## 5、WeakHashMap
&emsp;&emsp;    使用数组 + 链表的结构；数组里面存的Entry类继承了WeakReference接口，既Entry对象是弱引用；弱引用不影响对象GC，所以    
            当WeakHashMap添加的(key,value)没有其它强引用引用时，WeakHashMap对(key,value)的引用不影响key,value的GC。
&emsp;&emsp;    扩容机制：加载因子0.75，默认长度16.    
&emsp;&emsp;    查询复杂度：最好O(1)，最坏O(n).
[弱引用](http://note.youdao.com/noteshare?id=1ffe5f5d4dca01b199c34cc5e3a67534&sub=1E62210F89294B49BD175A9AFD37121C)


# Set
**&emsp;&emsp;    元素唯一**

## 1、HashSet
&emsp;&emsp;    TreeMap本质上是一个HashMap，只是所有的value值均为一个static final Object对象，并不是null；这么做的原因之一是，
            remove的是时候会返回value值，当删除的key不存在，删除失败的时候会返回null，如果我们本身存的value也是null，则无法判断是否  
            删除成功。
&emsp;&emsp;      

## 2、TreeSet
&emsp;&emsp;   和HashSet类似，TreeSet本质上是一个TreeMap，所有的value均为一个static final Object对象。


#Queue
**&emsp;&emsp;    仅能操作头、尾节点，不能像链表操作中间节点**
## 1、ArrayDeque
&emsp;&emsp;   基于数组实现的双端队列，头部和尾部都能添加、获取元素。
## 2、PriorityQueue
&emsp;&emsp;   通过二叉小顶堆实现，可以用一棵完全二叉树表示，Comparator实现的具有优先级排序的队列。
        
        

# 总结一
&emsp;&emsp;   数组、链表、红黑树、键值对；java容器是基于这四个基础结构上进行组装实现了各种结构，对于使用数组的容器均有扩容的概念。  
            然后根据需求加上相应的配件实现特殊的功能，配件如下；  
&emsp;&emsp;   (1)synchronized：保证线程安全；Vector,HashTable；  
&emsp;&emsp;   (2)Comparator：排序顺序；PriorityQueue,TreeMap,TreeSet；  
&emsp;&emsp;   (3)equals、hashCode、地址值：保证唯一。  
&emsp;&emsp;   (4)WeakReference：若引用。  

# 总结二
&emsp;&emsp;   iterator：迭代器是Collection类型集合（非Map，基于链表、数组的集合）设计的时候默认获取的一个迭代器，用于遍历元素；  
            但是Iterator只是提供了相关规范，核心方法就是hasNext(),Next()和remove；而具体的实现由具体的实现类根据自己的需求去实现；
            并且remove()方法应该和next()方法是绑定在一起，既获取最后一次获取的元素。

