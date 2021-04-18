package com.up.jdk.feature;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-04-16 17:38
 */
public class ThreadLocalDemo {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("a");

        System.out.println(threadLocal.get());
        threadLocal.set("b");
        System.out.println(threadLocal.get());


    }
}
