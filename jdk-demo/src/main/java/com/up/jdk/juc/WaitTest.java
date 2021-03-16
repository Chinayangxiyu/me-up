package com.up.jdk.juc;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yxy
 * @date 2020/5/30 14:35
 * @description
 */
public class WaitTest {

    public static void main(String[] args){

        Thread t1 = new Thread(new MeThread());

        t1.start();
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t1.interrupt();
        WaitTest.class.notifyAll();

        while(true){

        }

    }

    public static void method1(){
        synchronized (WaitTest.class){
            try{
                WaitTest.class.wait();
                System.out.println("this method1");

            }catch (InterruptedException e){
                e.printStackTrace();
            }


        }
    }
}

class MeThread implements Runnable{

    @Override
    public void run() {
        WaitTest.method1();
    }
}
