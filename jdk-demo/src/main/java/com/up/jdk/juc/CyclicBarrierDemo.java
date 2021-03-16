package com.up.jdk.juc;

import java.util.concurrent.CyclicBarrier;

/**

 */
public class CyclicBarrierDemo {

    public static void main(String[] args) throws Exception{

        CyclicBarrier barrier = new CyclicBarrier(3);

        Thread t1 = new Thread(()->{
            System.out.println("thread 1 arrive");
            try {
                // 线程1睡眠，其它线程抵达到屏障时会被拦截，不会继续执行；直到线程1恢复后 抵达屏障处
                Thread.sleep(500);
                barrier.await();

                System.out.println("thread 1 go on");
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            System.out.println("thread 2 arrive");
            try {
                barrier.await();

                System.out.println("thread 2 go on");

            }catch (Exception e){
                e.printStackTrace();
            }
        });


        Thread t3 = new Thread(()->{
            System.out.println("thread 3 arrive");
            try {
                barrier.await();

                System.out.println("thread 3 go on");

            }catch (Exception e){
                e.printStackTrace();
            }
        });

       t1.start();
       t2.start();
       t3.start();
// 可以重置屏障，但是如果
//       Thread.sleep(100);
//       barrier.reset();
    }

}
