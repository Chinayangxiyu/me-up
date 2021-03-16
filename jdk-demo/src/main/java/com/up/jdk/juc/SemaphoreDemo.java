package com.up.jdk.juc;

import java.util.concurrent.Semaphore;

/**
 * 信号量demo
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        for(int i = 0; i < 10; i++){
            Thread t = new Thread(new SemaphoreThread(i, semaphore));
            t.start();

        }
    }
}


class SemaphoreThread implements Runnable{

    int sign;
    Semaphore semaphore;

    public SemaphoreThread(int sign, Semaphore semaphore) {
        this.sign = sign;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {


        try {
            semaphore.acquire();
            System.out.println("acquire success:" +sign);
            Thread.sleep(50);

            System.out.println("release :" +sign);
            semaphore.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}