package com.up.jdk.feature.jdk8;

/**
 * @author yxy
 * @date 2020/4/22 14:35
 * @description
 */
@FunctionalInterface
interface FilterProcessor<T>{
    boolean process(T t);
}
