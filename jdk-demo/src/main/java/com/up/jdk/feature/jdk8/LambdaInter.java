package com.up.jdk.feature.jdk8;

/**
 * @author yxy
 * @date 2020/4/18 14:52
 * @description
 */
// check current interface
@FunctionalInterface
public interface LambdaInter<T> {

    // 函数式接口方法
    T changeAge(T t);

    static void  staticMethod(){
        // this static mehod
    }

    default void defaultMethod1(){
        // multi default method1
    }
    default void defaultMethod2(){
        // multi default method2
    }
}
