package com.up.jdk.juc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author yxy
 * @date 2020/5/31 17:11
 * @description
 */
public class DelayQueueTest {


    public void method2(){
        DelayQueue<child> d1 = new DelayQueue();
//        d1.add()
//        d1.take();
    }
}


class child implements Delayed {

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}