package com.up.jdk.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yxy
 * @date 2020/5/12 22:38
 * @description
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                lock.l
//                lock.lockInterruptibly();
                lock.lock();
                try {
//                    while(true){
////                        System.out.println("t1" + "----------------");
//                    }

                    Thread.sleep(200L);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("t2" + "*****************");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

        Thread.sleep(100);
        t1.interrupt();

    }

}
