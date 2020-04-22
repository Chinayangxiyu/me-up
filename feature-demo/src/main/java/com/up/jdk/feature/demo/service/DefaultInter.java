package com.up.jdk.feature.demo.service;

import com.up.jdk.feature.demo.base.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 */
public interface DefaultInter {


    // 抽象的默认方法，使得代码层次结构更简洁
    default void method1(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("kangkang", 20, 1));
        list.add(new Person("marin", 22, 0));
        list.add(new Person("jack", 18, 1));

        list.stream().forEach(p -> System.out.println(p.getName()));
    }



}
