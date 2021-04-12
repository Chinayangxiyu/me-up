# һ��JUC���

JUC��Java util concurrent����Java�������߰���
JUC�ĺ��ģ�AQS��volatile��CAS���߳��жϡ�LockSupport  
[�ṹ��ͼ](./resources/JUC˼ά��ͼ.png)��

## ����֪ʶ

�߳��жϣ��߳��ж��ǵ��ñ��ط������߳�һ����ǣ������ж�ʱ����ϵͳ�ж���  
static interrupted���÷����᷵��**��ǰ�߳�**�Ƿ��жϲ�**�����ж�״̬**��
isInterrupted���ж��߳��Ƿ��жϣ���������״̬�� interrupt�������߳��жϡ�
java.util.concurrent.locks.AbstractQueuedLongSynchronizer.Node��JUC�ײ�ʵ��ͨ�õĶ��С�


# ����locks

## 2.1 Lock��ReadWriteLock

Lock����������ӿڣ��Ա�synchronized�����ܿ��жϵĻ�ȡ������ʱ��ȡ����
ReadWriteLock�������ȡ������д���Ľӿڣ���д��Ҳ��Lock��

## 2.2 Condition

�������ṩ�ȴ��������̵߳Ĺ��ܣ���Object.wait()��notify()����һ����������Lock�ӿڡ�
ԭ��Conditionʹ��Nodeά���ȴ����߳�������У�AQSά������ͬ�����У����ֵȴ����ƺ�synchronize
����  
[����ͼ](/3-extend/3-3����/resources/monitor����ͼ.png)��  
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/ConditionDemo.java#L8-L29)


## 2.3 LockSupport

�������ṩ��������������ķ�����֧���ж���Ӧ�ͳ�ʱ��
ʵ��ԭ��park��unpark���õ�Posix�߳̿�pthread�е�mutex������������condition������������ʵ�ֵģ�  
mutex��condition������һ����_counter���ź���������parkʱ_counter��Ϊ0������unparkʱ��_counterֵΪ1��  
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/LockSupportDemo.java#L6-L61)

## 2.4 ReentrantLock

�����������������ʵ���˹�ƽ���ǹ�ƽ�� �����룺stateֵ������������  
��ƽ�����ͷź�ỽ�ѵȴ����еĵ�һ���ڵ�ȥ��ȡ��Դ���ϸ���FIFO���д���  
�ǹ�ƽ�������ͷź����̻߳�ȡ��ֻ��Ҫ�滻stateֵ�ɹ����ܻ�ȡ����ͬ�������е��߳���Ҫ�������ѵȲ�������  
��ȡ�������������̸߳��죻��ͬ�����еĽڵ㻽�Ѻ����state��Դ�Ѿ������߳������ˡ����ѵĽڵ�û������Դ��  
��ָ�������״̬��

ԭ����ƽ����ȡʱ�����hasQueuedPredecessors()�����ж�ͬ�������Ƿ�Ϊ�գ�����Ϊ���򽫵�ǰ�����װ���  
�����У����ͬ������Ϊ������CAS��ȡ�������ǹ�ƽ���������hasQueuedPredecessors()�����жϣ�ֱ�ӳ���CAS  
��ȥstate��Դ��

��������ƽ����Ϊ�ϸ���FIFO���д���ÿ�δ�����Ҫ�л������ģ��ǹ�ƽ�������л������������������������ǿ���  
�����̼߳������⣨ͬ�������е��̳߳��ڻ�ȡ����������  
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/ReentrantLockDemo.java)


## 2.5  ReentrantReadWriteLock

�������������д����ͬһ�߳̿���ͬʱ��ȡ����д�������̼߳䣬��-��������ģ���-д��д-д������ġ�  
����ԭ��state��int���͵�ֵ��һ����32λ����16λ��ʾ��������16λ��ʾд����λ��ֵ��ʾ���������������λ��ֵ  
��ʾд�����������  
��������ȡ����ʱ���state�Ƿ������̱߳��Ϊд�������߶����Ļ�ȡ�������ޣ�����ᱻ��ӵ����У�������CAS�޸�stateֵ��  
д������ȡд��ʱ���state�������̱߳��Ϊд�����������ǰ�߳���ӵ�ͬ�������С�  
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/ReentrantReadWriteLockDemo.java)

### ��д������
��1��ReentrantReadWriteLock֧�ֹ�ƽ���ǹ�ƽ�����ù�ƽ���󣬻�ȡ��ʱ���ж�ͬ�������Ƿ�Ϊ�գ�  
��ƽ�Ķ�д���ή��Ϊ��ͨ��ReentrantLock���Ȳ���ʵ�ֲ����������÷ǹ�ƽ����д����ȡʱֱ�ӳ���  
CAS��������ȡʱ����ж϶���ͷ�ڵ��Ƿ�Ϊ"����"ģʽ��



## 2.6 AbstractQueuedSynchronizer

������AQS��AbstractQueueSynchronizer������ͬ��������JUC�ײ�ʵ������CountDownLatch��  
ͬ������ʵ�ֵĻ������ڲ�ά��һ��volatile���ε�int������һ��FIFO�Ķ�����Ϊ�����ܹ������  
CAS������LockSupport��park��unpark����ʵ����һ��������ͬ�����п�ܡ�

### ����˵��

**state����**��ʵ�ֶ�ռ��ʱstateĬ����0����ȡ������1��ʵ�ֹ�����ʱstateֵĬ����0����ȡһ�μ�1  
�ͷ�һ�μ�1��ʵ��CountDownLatch�ȹ���ʱ��state����Ӧ�ļ�������  
**Node(head)����**��ʵ�ֶ�ռ��ʱδ�ܻ�ȡ����ʱ�����ݵ�ǰ�̴߳���һ��Node��ӵ�����β����tail����ʵ��  
������ʱ������������ȡ�������˺󣬻Ὣ��ǰ���񴴽�һ��Node��װ�������С�
**nextWaiter**����ʾ���������нڵ��ģʽ���Ƕ�ռģʽÿ��ֻ����һ���ڵ��ڴ��������Ľڵ���Ҫ�����ѣ�
������ģʽ�Ľڵ㣬û��������һֱ���󴫵ݡ�

### ��Ҫ����

tryAcquire�������Զ�ռģʽ��ȡ����state��Դֻ��һ����  
tryRelease���ͷŶ�ռ��Դ��  
tryAcquireShared�����Ի�ȡ������Դ����state��Դ�ж����  
tryReleaseShared�������ͷŹ�����Դ��  
acquire����ռģʽ��ȡ��ʧ�ܵĻ�����ǰ�̷߳�װΪNode��ӵ����У�ֱ�������ѣ�  
acquireInterruptibly����ռģʽ��ȡ��ʧ�ܵĻ��������̷߳�װΪNode��ӵ����У���acquire��ͬ����  
������������Ӧ�жϣ��߳��ڵȴ���ȡ��Դ�ڼ䣬�������̵߳����жϷ�������׳��ж��쳣����acquire����  
�е��߳������ٱ������̵߳����жϺ󣬻������ж�״̬��  
tryAcquireNanos����ռģʽ��ȡ��ʧ�ܺ���������У����õȴ���ʱʱ�䣨LockSupport.parkNanos�����  
��ʱδ���ط���false����ȡʧ�ܣ�������жϻ��׳��ж��쳣��

acquireShared������ģʽ��ȡ����ȡʧ���̼߳�����У�ֱ����ȡ�ɹ���  
acquireSharedInterruptibly������ģʽ��ȡ����ȡʧ�ܼ�����У�ֱ����ȡ�ɹ������̱߳��жϣ��׳��쳣��  
tryAcquireSharedNanos������ģʽ��ȡ����ȡʧ�ܼ�����У�ֱ����ȡ�ɹ�����ʧ�ܣ����߳��ж����쳣��

release���ͷŶ�ռ��Դ�������Ѷ��еĺ�̽ڵ㣻  
releaseShared���ͷŹ�����Դ�������ѵ�ǰ�ڵ�ĺ�̽ڵ㡣

#### AQS�ܽ�

�ܽ᣺�ڲ�ͬ��ʵ����state���Ժ��岻ͬ��

# ����tools

## 3.1 CountDownLatch

����������һ�������̵߳ȴ���ֱ�������߳�ִ�����Ӧ�Ĳ�����һ����������ȴ��߳̿�����Ӧ�жϡ����ó�ʱʱ�䡣  
ԭ��stateΪCountDownLatch��������ÿ����һ��countDown()�������1��ֱ��Ϊ0ʱ�����ѵȴ����̡߳�  
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/CountDownLatchDemo.java)

## 3.2 CyclicBarrier

�������ȵ�һ���߳�ȫ���ִ��������ش�ʱ���������زŻ���У�һ��ʼ����  
ԭ��stateָ���߳����������ÿһ���̵ִ߳�����ʱstate��1��stateΪ0ʱʹ��Condition.signalAll()  
�������е��̡߳�
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/CyclicBarrierDemo.java)
���䣺CyclicBarrier�ļ������������ã����Կ��Ը��ã�����ʱ�������ǰ�����߳��ڵȴ�����BrokenBarrierException��

## 3.3 Semaphore

�������ź����������޶������ض���Դ���߳��������������������ǰû����Դ����ӵ������еȴ���  
ԭ��state�������̻߳�ȡ���֤ʱstate��1���߳�ʹ�ý�����黹���֤state��1�������Ի��ѵȴ�������ȥ����
��ȡ���֤��
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/SemaphoreDemo.java)

## 3.4 Exchanger

�������ṩ�̼߳佻�����ݵķ�����
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/ExchangerDemo.java)


# �ġ�collections

�̰߳�ȫ�ļ���

## 4.1 ConcurrentLinkedDeque(����)
�޽�˫�˶��У�ʹ��CAS��װ�̰߳�ȫ��

## 4.2 BlockingQueue���������У�
�������У�֧�������Ĳ�����Ƴ�������������ʱ���������̣߳�����Ϊ��ʱ�����Ƴ��̡߳�

| ����Ϊ�ջ��������ʱ���������صĽ�� | ���쳣  | ����true/false | һֱ���� | ��ʱ�˳�             |
|:--------------------------------|:-------|:--------------|:--------|:---------------------|
| ���                            | add(e)    | offer(e)    | put(e)  | offer��e,time,unit�� |
| �Ƴ�                            | remove(e) | poll()      | take    | poll(time,unit)     |

ArrayBlockingQueue������ṹ�ģ��н���������У��ڲ�ʹ��ReentrantLockά����ƽ���ǹ�ƽ����  ��
DelayQueue�����ȼ�����PriorityQueue���޽�����������DelayedԪ�أ�����ͷ�����ӳ�ʱ��������  
��Ԫ�أ�ȡ����Delayed��ʵ�֣�������ȡԪ��ʱ�ӳ�ʱ��δ������������  
LinkedBlockingQueue�������н�������  
PriorityBlockingQueue�����ȼ�����PriorityQueue���޽�������  
SynchronousQueue��û���������������У�ÿ�β��붼����ȴ���һ���̵߳�ɾ��������  
LinkedTransferQueue������ṹ���޽��������ṩtransfer��������������û��������һֱ������ֱ�����������Ѻ��ѡ�


## 4.3  LinkedBlockingDeque������˫�˶��У�
����ṹ��˫���������С�

## 4.4 CopyOnWriteArrayList
�̰߳�ȫ��ArrayList��ά��volatile���ε����飬��֤���������µģ�ʹ��ReentrantLock��֤д�İ�ȫ�ԡ�

## 4.5 CopyOnWriteArraySet
�̰߳�ȫ��Set��ʹ������洢Ԫ�أ�Ч�ʺܵͣ�������ɾ����������copyһ�����ݡ�

## 4.5 ConcurrentSkipListSet
�̰߳�ȫ�����򼯺ϣ�����TreeSet���ײ���ConcurrentSkipListMapʵ�֡�

## 4.6 ConcurrentMap
ConcurrentSkipListMap���ײ�ʹ������ʵ�֣�����ģ��̰߳�ȫ��Map��  
ConcurrentHashMap���ײ�ʹ�����ݡ����������ṹ��ʹ��CAS��synchronized��֤�̰߳�ȫ��


# �塢�̳߳�
## 5.1 Future
�첽��ȡ�̼߳�����������δ��������̣߳�Ҳ����ȡ���߳�ִ�С�
ScheduledFuture����Future�ӿڵĻ�������չ��Delayed�ӿڣ�Ϊִ�ж�ʱ���ӳ�������̳߳��ṩ������ӿڡ�
FutureTask���첽�����ʵ�֣�ÿһ��FutureTask�������һ��Runnable���񣬵������̵߳���ʵ����get����ʱ��
���Runnableִ��û�����������get���̻߳ᱻ������֪��run����ִ�н�����ȥ���ѡ�

## 5.2 Callable
�첽�����ʱ����Ի�ȡ���������׳��쳣��������FutureTask����Runnable�Ļ�����װ����ǿ��
ӵ�з���ֵ�Ĺ��ܡ�

## 5.3 �̳߳�
ExecutorService���̳߳ؽӿڡ�
ThreadPoolExecutor���̳߳�ʵ���࣬���������߳�����������С�����߳����������̴߳��ʱ�䡢�ܾ����ԵȲ�����


ScheduledExecutorService���ӳٻ���ִ�е��̳߳ؽӿڡ�
ScheduledThreadPoolExecutor���ӳ١�����ִ��������̳߳أ���ThreadPoolExecutor�Ļ�������չ�˶�ʱ������ִ������Ĺ��ܡ�
[ʾ��](../../jdk-demo/src/main/java/com/up/jdk/juc/ExecutorDemo.java)

RejectedExecutionHandler���ܾ����ԵĽӿڣ�Ĭ�����ĸ��������Զ���ʵ�֡�
Executors�������̳߳صĹ����ࡣ

## 5.4 CompletableFuture
jdk1.8��Future����չ����ǿ���첽�ص�����ʽ�������Future��ϴ����������ʹ��Java�������Э���������������  
������һ��ʹ��CompletableFuture�ľ�̬������ȡʵ����

### �ύ����ķ�������̬������
CompletableFuture.runAsync(Runnable runnable):�޷���ֵ�����ύ��ʹ��Ĭ���̳߳�ForkJoinPool��  
CompletableFuture.runAsync(Runnable runnabl, Executor executor):�޷���ֵ�����ύ��ʹ�ô�����̳߳�ִ�С�  
CompletableFuture.supplyAsync(Supplier<U> supplier):�з���ֵ�����ύ��ʹ��Ĭ���̳߳�ForkJoinPool��  
CompletableFuture.supplyAsync(Supplier<U> supplier, Executor executor):�з���ֵ�����ύ��ʹ�ô�����̳߳ء�

### ʵ�������������첽�ص�����ʽ����
��1���첽�ص������Future���첽�ص����������׶�
```
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return " execute supply";
        });
        // ��future�ķ��ؽ����Ϊ��������thenApply�����첽�ص�����
        future.thenApply((param)->{

            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("thenApply" + param);
            return "aa:" + param;
        });
        
        System.out.println("main is ending");

```
��2����ʽ����:�����Future������ʽ��������ʵ��̫����
```
 CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return " execute supply";
        });

        future.thenApply((result) ->{
            System.out.println(result + " thenApply");
            return result + " thenApply";
        })
                .thenAccept((result) -> {
                    System.out.println(result + " thenAccept");
                })
                .thenRun(() -> {
                    System.out.println("thenRun");

                });

        System.out.println("pipeline ending");
```


(�ο�1)\[https://blog.csdn.net/qq_31865983/article/details/106137777]  
(�ο�2)\[https://www.jianshu.com/p/558b090ae4bb?from=timeline]



# �ġ�atomic

����volatile���εı������ṩ�̰߳�ȫ��ԭ�Ӳ�����
AtomicIntegerFieldUpdater�����ڷ��䣬�ṩ��volatile���ε�Integer�������Ե�ԭ�Ӳ�����
AtomicLongFieldUpdater�����ڷ��䣬�ṩ��volatile���ε�Long�������Ե�ԭ�Ӳ�����
AtomicReference���������õ�ԭ�Ӳ�����
AtomicReferenceFieldUpdater�����ڷ��䣬�ṩ��volatile���ε�Reference�������Ե�ԭ�Ӳ�����


# ��������

## 1����������ʵ��ԭ��
��1�������Ĳ�����Ƴ�Ϊ�������У�ʵ��ԭ����ͨ��Conditionʵ���̼߳��ͨ����ʵ�ֵġ�  
��2����Conditionʵ�ֵ��̼߳�ͨ���ǻ���LockSupport.park()��unpark()ʵ�ֵģ�  
��3���ײ�ͨ������Unsafe�ı��ط�������̵߳ĵȴ��ͻ��ѣ�Unsafe.park��ӦLinux��ϵͳ����  
pthread_cond_wait��Unsafe.unpark()��ӦLinux��ϵͳ����pthread_cond_signal��

## 2����ʱ�����������̳߳�(ScheduledThreadPoolExecutor)ʵ��ԭ��
��1����ʱ�����̳߳ص����������DelayQueueʵ�֣���DelayQueue�ڲ�ά��������Delayed  
Ԫ�ع��ɳɵ�PriorityQueue�����ȼ������У�PriorityQueue����DelayedԪ�ص�ʣ�����  
ʱ�����˳�����С�  
��2����ΪDelayQueue���ӳٶ��У�����ScheduledThreadPoolExecutor��DelayQueue�л�ȡ  
����ִ��ʱ�����������ʱ��δ���������ݵ���ʱ��͵�ǰʱ������һ���ȴ�ʱ��(nanosTimeout)��  
��ǰ�߳�����ᱻ������ֱ���ȴ�ʱ�䳬ʱ��

## 3��