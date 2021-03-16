package com.up.jdk.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-02-03 14:28
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        // 默认是非公平锁，非公平锁打印大部分锁连续的值（一个线程打印100到1，然后下一个线程再打印）；
        // 既释放后由上次获取锁的线程重新设置state获取到锁
//        ReentrantLock lock = new ReentrantLock();

        // 严格按照线程顺序打印的
        ReentrantLock lock = new ReentrantLock(true);

        Thread t1 = new Thread(new A(lock, "A"));
        Thread t2 = new Thread(new A(lock, "B"));
        Thread t3 = new Thread(new A(lock, "C"));

        t1.start();
        t2.start();
        t3.start();
    }



}

class A implements Runnable{

    int count= 100;
    Lock lock;
    String name;

    public A(Lock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {

        while (count >=0){
            lock.lock();

            try {

                System.out.println(name + ":" + count--);
            }finally {
                lock.unlock();
            }
        }

    }
}
