package com.up.jdk.feature.jdk8;

import com.up.jdk.feature.base.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author yxy
 * @date 2020/4/18 16:55
 * @description
 */
public class PersonService {


    public void change(List<Person> list, LambdaInter<Person> lambda){
        list.forEach(p ->{
            lambda.changeAge(p);
        });
    }



    public static void main(String[] args) {
        PersonService service = new PersonService();
        List<Person> list = new ArrayList<>();
        list.add(new Person("张三",20,1));
        list.add(new Person("翠花",10,0));
        service.change(list, (Person p) ->{
            if(p.getGender() == 0){
                p.setAge(p.getAge() -2);
            }else{
                p.setAge(p.getAge() +1);
            }
            return p;
        });

        list.forEach(p -> System.out.println(p.getAge()));

        list.add(new Person("翠花",8,0));
        list = list.stream().distinct().collect(Collectors.toList());

        list.add(new Person("jack",8,0));
        list = list.stream().limit(2).collect(Collectors.toList());

        list.stream().mapToDouble(Person::getAge);

//        Set<Person> set = list.stream().collect(Collectors.toSet());
        list.forEach(p -> System.out.println(p));
        ConcurrentMap<String, Integer> currentMap =  list.stream().collect(Collectors.toConcurrentMap(Person::getName, Person::getAge));


    }
}

