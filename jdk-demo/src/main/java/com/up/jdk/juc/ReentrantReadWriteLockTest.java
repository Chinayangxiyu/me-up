package com.up.jdk.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yxy
 * @date 2020/5/30 16:53
 * @description
 */
public class ReentrantReadWriteLockTest {

    Lock writeLock;
    Lock readLock;

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
        ReentrantReadWriteLockTest demo = new ReentrantReadWriteLockTest(lock.writeLock(), lock.readLock());

        Thread t1 = new Thread(()->{

            demo.get();
        }, "read thread 1");

        Thread t2 = new Thread(()->{

            try {
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
            demo.get();
        }, "read thread 2");


        Thread t3 = new Thread(()->{

            try {
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            demo.add("写入");
        }, "write thread 1");

        t1.start();
        t2.start();
        t3.start();


    }

    public ReentrantReadWriteLockTest(Lock writeLock, Lock readLock) {
        this.writeLock = writeLock;
        this.readLock = readLock;
    }

    private String get(){
        readLock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());

            return Thread.currentThread().getName();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }

        return Thread.currentThread().getName();
    }


    private void add(String s){
        writeLock.lock();
        try{

            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());

        }catch (Exception e){

        }finally {
            writeLock.unlock();
        }

    }
}
