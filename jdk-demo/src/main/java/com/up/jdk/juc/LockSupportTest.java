package com.up.jdk.juc;

import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static String blocker = "str";

    public static AtomicInteger count = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            LockSupportTest.get();
        });

        t1.start();

        Thread.sleep(100);
        t1.interrupt();
    }





    public static void get(){

        System.out.println( Thread.currentThread().isInterrupted());
        if(count.get() <= 0){
            LockSupport.park(blocker);
        }
        System.out.println( Thread.currentThread().isInterrupted());



        if(count.compareAndSet(0,1)){
            System.out.println("获取一个信号");
        }

    }

    public static void add(){
        int c = count.get();
        if( c >= 8){
            LockSupport.park(blocker);
        }

        if(count.compareAndSet(c,c+1)){
            System.out.println("归还一个");
        }

    }
}
