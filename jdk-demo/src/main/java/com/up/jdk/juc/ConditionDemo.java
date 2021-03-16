package com.up.jdk.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition使用示例，实现生产、消费者模式
 */
public class ConditionDemo {

    public static volatile AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {

        // 创建公平锁
        ReentrantLock lock = new ReentrantLock(true);
        Condition fullCondition = lock.newCondition();
        Condition emptyCondition = lock.newCondition();

        Thread t1 = new Thread(new Product(fullCondition, emptyCondition, lock));
        Thread t2 = new Thread(new Customer(fullCondition, emptyCondition, lock));


        t1.start();
        t2.start();

    }
}


/**
 * 生产者
 */
class Product implements Runnable {

    Condition fullCondition;
    Condition emptyCondition;
    Lock lock;

    public Product(Condition fullCondition, Condition customerCondition, Lock lock) {
        this.fullCondition = fullCondition;
        this.emptyCondition = customerCondition;
        this.lock = lock;
    }

    @Override
    public void run() {
        // 生产者尝试一直生产
        while(true){
            // 获取锁成功
            lock.lock();
            try {
                // 产品已满
                if(ConditionDemo.count.get() == 10){
                    try {
                        // 通知消费者
                        fullCondition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // 生产产品
                ConditionDemo.count.getAndIncrement();
                System.out.println("生产一个产品，剩余"+ConditionDemo.count.get());
                // 通知消费端可以消费
                emptyCondition.signal();

            }finally {
                lock.unlock();
            }

        }

    }
}

class Customer implements Runnable {

    Condition fullCondition;
    Condition emptyCondition;
    Lock lock;


    public Customer(Condition productCondition, Condition customerCondition, Lock lock) {
        this.fullCondition = productCondition;
        this.emptyCondition = customerCondition;
        this.lock = lock;
    }

    @Override
    public void run() {
        // 模拟一直消费
        while(true){
            lock.lock();
            try {
                // 商品数量小于等于0，阻塞消费消除
                if (ConditionDemo.count.get() <= 0) {
                    try {
                        emptyCondition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 消费
                ConditionDemo.count.decrementAndGet();
                System.out.println("消费一个产品，剩余"+ConditionDemo.count.get());
                // 通知生产端可以生产
                fullCondition.signal();

            }finally {
                lock.unlock();
            }

        }

    }
}