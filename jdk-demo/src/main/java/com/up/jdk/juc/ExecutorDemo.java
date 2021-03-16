package com.up.jdk.juc;

import java.util.concurrent.*;

public class ExecutorDemo {

    public static void main(String[] args){

        // 1、使用线程池工厂获取线程池
        Executors.newFixedThreadPool(10);

        // 2、自定义参数创建线程池
        BlockingQueue queue = new ArrayBlockingQueue(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 5,
                1000L, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.AbortPolicy());

        // 提交Runnable任务
        executor.submit(()->{
            System.out.println("提交runnable任务");
        });

        // 提交Callable任务
        Future<String> future = (Future<String>) executor.submit(()->{

            System.out.println("提交Callnable任务");
            return "result";

        });

        // 获取异步结果
        try {
            future.get();
        }catch (Exception e){
            e.printStackTrace();
        }


        // 3、支持延迟执行、定时执行的线程池
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledExecutor.schedule(()->{
            System.out.println("线程池任务延迟执行");
        }, 1000, TimeUnit.SECONDS);
    }
}
