package com.up.jdk.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CallableDemo {

    public static void main(String[] args){

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> callable = new Callable() {


           @Override
           public String call() throws Exception {
               return "";
           }
       };

        executor.submit(callable);
    }
}
