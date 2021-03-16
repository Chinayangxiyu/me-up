package com.up.jdk.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static String blocker = "str";

    public static AtomicInteger count = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            LockSupportDemo.get();
        });

        t1.start();

        Thread.sleep(100);
        // 解除阻塞
        LockSupport.unpark(t1);

        // 可以响应中断
        //t1.interrupt();
    }





    public static void get(){
        // 当前线程中断状态 false
        System.out.println( Thread.currentThread().isInterrupted());
        if(count.get() <= 0){
            // 阻塞
            LockSupport.park(blocker);
        }
        // 如果当前线程是被中断唤醒的，打印true，是被unpark唤醒的答应你false
        System.out.println( Thread.currentThread().isInterrupted());



        if(count.compareAndSet(0,1)){
            System.out.println("获取一个信号");
        }

    }
//
//    public static void add(){
//        int c = count.get();
//        if( c >= 8){
//            LockSupport.park(blocker);
//        }
//
//        if(count.compareAndSet(c,c+1)){
//            System.out.println("归还一个");
//        }
//
//    }
}
