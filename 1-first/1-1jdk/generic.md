# 1�� ThreadLocal

## 1-1������
�̱߳��ر�����ÿ���̶߳���һ��ThreadLocalMap��ά����ǰ�̵߳�һЩ������
ͨ��set()��get()��remove()������ά��ThreadLocalMap�е�ֵ��

## 1-2 ThreadLocalMap
��1��ThreadLocalMap�ڲ�ά������Entry���飬Entry�̳���WeakReference�����ڹ�����  
�а�װThreadLocal����Ϊ�����ã�����ֱ�ӵ���Reference�ķ�����������Entry.get()����  
ʱ��ʵ�ʵ���Reference��get()������������ڷ������õ�ַ�����򷵻�null��
```
  static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k); // ��װthreadLocal����Ϊ������
                value = v;
            }
        }
```

��2��ThreadLocalMap�����ϣ��ͻ��ͨ������ThreadLocal����Ĺ�ϣֵ�ж϶�Ӧ�����������������ֹ�ϣ��ͻʱ  
**ʹ�ÿ���Ѱַ�������������Ԫ�أ��ҵ��յ�λ�ñ���Entry**�����û�ҵ��յ�λ�ã���Ҫ���ݡ�
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

��3��get()��������ȡԪ��ʱ�ȼ���ThreadLocal�Ĺ�ϣֵ�ҵ�EntryԪ�أ�Ȼ�����Reference.get()  
������ȡ��װ��ThreadLocal�������õ�ַ���Ƚ����õ�ַ�Ƿ���ͬ����ͬ�򷵻أ�������������ҵ�Ԫ�ء�


**�ܽ�1��ThreadLocalMapʹ�ÿ���Ѱַ�����ϣ��ͻ�����ַ���ֻ�ʺ�����Ԫ�ء���ϣ��ͻ���ٵ������  
���Ԫ�ء���ϣ��ͻ���࣬��ô�����������������ܡ���HashMapʹ���������ϣ��ͻ���ȶ���**

## 1-3��ThreadLocal�ڴ�й©
��1��ThreadLocalMap�ڲ�ά��һ��Entry\[\]���鱣��Ԫ�أ�  
��2��Entry�̳���WeakReference����ͨ��������ȥ��װ�����ThreadLocal����ʹ��  
Entry���õ�ThreadLocal������һ�������á�

��3����ʱ��������·Ϊ currentThread -> ThreadLocalMap -> Entry\[\] -> threadLocal��value����  
��4�������ʱ������GC����ΪEntry��threadLocal�����ù�ϵ����װ���������ã������ʱthreadLocalû����  
�����ط������ã�ǿ���ã�����ô���threadLocal���ᱻ���ա�  
��5�����threadLocal�������ˣ����ǣ�currentThread -> ThreadLocalMap -> Entry\[\] -> value��  
���������һֱ���ڣ����Thread�������գ���ôentry���󽫻�һֱռ���ڴ��޷����ա�  
��6��threadLocal�����պ󣬾��޷��ҵ���ʷ��value��


**�ص㣺ʹ��ʱһ�㶼����һ��static final���ε�ThreadLocal��������ThreadLocal����  
һֱ��һ��ǿ���ý��й�������Ȼ��Entry��װ���������ã�������Ϊstatic final��֤��ǿ���ã�ThreadLocal  
һֱ���ᱻ���ա��������������ʹ�ú󲻵���remove()�������͵���ʹ�ù����Ѿ���Ч��Entry�����  
Ӧ�õ��������ڰ���һ���ˣ���Ҳ�������ڴ�й©�������**