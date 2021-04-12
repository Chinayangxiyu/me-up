package com.up.jdk.feature.jdk8;

import com.up.jdk.feature.base.Person;
import com.up.jdk.feature.base.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-04-07 10:33
 */
public class StreamFeature {


    public static void main(String[] args) {
        Person p1 = new Person("张三", 18, 1);
        Person p2 = new Person("李四", 20, 2);
        Person p3 = null;

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Map<String, Person> map = list.stream().collect(Collectors.toMap(Person::getName, Function.identity(), (n1, n2) -> n1));


        List< Person> l1 = new ArrayList<>();
        Student s1 = new Student("张三", 18, 1, Arrays.asList(1D,2D,3D));
        l1.add(s1);

        Person p4 = l1.get(0);

    }
}
