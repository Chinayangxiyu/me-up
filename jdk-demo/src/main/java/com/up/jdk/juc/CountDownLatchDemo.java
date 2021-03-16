package com.up.jdk.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 测试demo
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws Exception{
        // 创建容量为3的门闩
        CountDownLatch latch = new CountDownLatch(3);

        // 任务集
        Thread t1 = new Thread(new Worker(latch, 1));
        Thread t2 = new Thread(new Worker(latch, 2));
        Thread t3 = new Thread(new Worker(latch, 3));

        t1.start();
        t2.start();
        t3.start();


        // 允许阻塞多个"主线程"，latch归零后，唤醒多个主线程
        Thread m1 = new Thread(()->{

            try {
                // 主线程1等待 latch唤醒
                latch.await();
                System.out.println("m1");
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        m1.start();
        System.out.println("await pre");
        // 主线程等待latch唤醒
        latch.await();

        System.out.println("await after");

    }
}

class Worker implements Runnable{

    CountDownLatch latch;
    int value;

    public Worker(CountDownLatch latch, int value) {
        this.latch = latch;
        this.value = value;
    }

    @Override
    public void run() {

        System.out.println(value);
        // 当前任务结束，门闩减1
        latch.countDown();
// 门闩的数量不一定要在线程任务里减少
//        for(int i = 0; i < 3; i++){
//            latch.countDown();
//
//        }

    }
}