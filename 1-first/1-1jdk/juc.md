Callable有返回值原理
      线程最终执行的是Runable.run()方法是亘古不变的；如果需要“返回值”那么就需要在run（）方法中赋值一个变量，  
      既在run（）方法中调用call（）方法，并将返回值赋值给某个属性。这种设计方法使用了适配器的设计模式，目标  
      接口Callable，适配者Runable实现类，适配器类是FutureTask，在FutrueTask中的Run方法中，会调用call（）  
      方法，并把返回值赋值给outcome，然后可以通过get（）方法回去outcome属性。主线程调用get（）的时候需要判断  
      当前任务 的执行状态（volatitle修饰的state属性），只要不等于NEW（0）；就可以返回。


# 1、 synchronizd
 （1）字节码指令：  
 synchronized编译后会在同步代码块前后加上，monitorenter和monitorexit指令，这两个指令需要指明监控对象（既我们说的锁对象，monitor）；  
 每次执行monitorenter指令的时候，首先尝试获取对象的锁，如果对象未被锁定或者当前线程已经拥  
 有了这个对象的锁，则锁的计数器加1；执行monitorexit时，锁计数器减1；当计数器为0时，锁被释放。  
 （2）加锁核心：  
 线程进入monitor对象的entrySet，尝试获取锁，成功后持有锁；未成功的线程阻塞进入wait队列；持有锁的线程执行结束后唤醒所有wait线程；wait线程进入entrySet重新抢锁。  
 （3）、加锁过程及锁升级：  
  synchronized锁升，有线程1获取锁的时候，首先是加偏向锁，再markword记录加偏向锁的线程ID；当再次有线程2尝试获取锁的时候，判断当前线程ID和markword中的线程ID是否  
  相同，如果相同则继续执行；否则判断线程1是否存活，已经不再存活的话线程2获取偏向锁，将线程ID记录在mark word，如果线程1存活还需要持有该锁的话，会暂停线程1，将markword  
  拷贝到线程1的栈帧中，mark word指针指向线程1的栈帧拷贝的mark word；升级为轻量级锁；线程2自旋等锁。当线程1执行完毕，释放锁，将markword复制回mark word；在此期间线程  
  二自旋获取锁，如果线程2没自旋没有结束，那么线程2获取锁，将mark word拷贝到栈帧，此时有线程3来获取锁，发现锁已经被线程2持有，线程3自旋获取锁，自旋获取失败后，将锁升级  
  为重量级锁，即mark word指针指向monitor对象，并将当前线程挂起存放在monitor对象的阻塞队列。线程2执行结束后会去尝试将mark word拷贝回去，拷贝失败后，释放锁，唤醒线程。

# 2、LockSupport
概述：LockSupport是JUC的核心之一，方法park()、unpark()可以等待、唤醒线程。（park、unpark调用的是Unsafe对应的方法）
wait/notify：必须和synchronized配合使用。
park/unpark：以Thread为操作对象，unpark可以唤醒任意线程，操作更灵活。

扩展：  
（1）功能：LockSupport是AQS的基础组件之一，如在获取state的时候如果无法获取调用LockSupport.park阻塞当前线程，直到其他线程唤醒。  
（2）中断：也是因为park、unpark可以响应线程中断，所以current基于此实现的锁，也是可中断的；相比synchronized有优势。  
（3）原理：park和unpark是用的Posix线程库pthread中的mutex（互斥量），condition（条件变量）实现的；mutex和condition保护了一个  
叫_counter的信号量。调用park时_counter置为0；调用unpark时，_counter值为1。


# 3、Condition