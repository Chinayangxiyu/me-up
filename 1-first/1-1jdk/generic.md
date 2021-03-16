## ThreadLocal
概述：线程本地变量，每个线程都有一个ThreadLocalMap来维护当前线程的一些变量。
常用方法：set()、get()、remove()
问题：内存泄漏问题，ThreadLocalMap保存的元素是ThreadLocalMap.Entry，Entry对象的key
都是一个弱引用类型的ThreadLocal对象；如果在remove之前ThreadLocal对象被GC回收；那么就
失去了指向Entry的的指针，这个Entry对象无法被GC，导致泄漏。