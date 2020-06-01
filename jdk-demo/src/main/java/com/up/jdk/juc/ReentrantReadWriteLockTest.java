package com.up.jdk.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yxy
 * @date 2020/5/30 16:53
 * @description
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.
        lock.writeLock().lock();
    }
}
