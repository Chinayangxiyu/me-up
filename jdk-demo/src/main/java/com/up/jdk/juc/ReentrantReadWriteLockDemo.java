package com.up.jdk.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁示例
 */
public class ReentrantReadWriteLockDemo {


    List<Integer> list = new ArrayList<>();
    Lock readLock;
    Lock writeLock;

    public ReentrantReadWriteLockDemo(Lock readLock, Lock writeLock) {
        this.readLock = readLock;
        this.writeLock = writeLock;
    }

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo(lock.readLock(),lock.writeLock() );

        // 使用写安全的方法添加元素
        Thread t1 = new Thread(() -> {
            for(int i =1; i <= 100; i++){
                demo.add(i);
                System.out.println("add"+demo.list.size());
            }
        });



        Thread t2 = new Thread(() -> {
            for(int i =0; i < 100; i++){
                System.out.println(demo.getOfIndex(i));
            }
        });

        t1.start();
        t2.start();

    }



    public Integer getOfIndex(int index){
        readLock.lock();
        try {
            if(!list.isEmpty()){
                return list.get(list.size()-1);
            }
            return null;
        }finally {
            readLock.unlock();
        }
    }


    public void add(int index){
        writeLock.lock();
        try {
            list.add(index);
        }finally {
            writeLock.unlock();
        }
    }
}


