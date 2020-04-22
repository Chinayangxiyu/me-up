package com.up.jdk.feature.demo.service;

import com.up.jdk.feature.demo.base.Person;
import com.up.jdk.feature.demo.base.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yxy
 * @date 2020/4/22 12:00
 * @description
 */
public class StudentService {

    public static void main(String[] args) {
        List<Double> s1Scores = Arrays.asList(89.5d, 90d, 100d,70d);
        Student s1 = new Student("jack", 12, 1,s1Scores );

        List<Double> s2Scores = Arrays.asList(78.5d, 81d, 92d,85d);
        Student s2 = new Student("marin", 13, 0,s2Scores );

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        // 先使用map获取成绩单List,然后将所有学生的成绩单平铺到一个流
        students.stream().map(Student::getScores).flatMap(List::stream).collect(Collectors.toList());
        students.stream().map(Student::getScores);
        List<Student> newStudents = new ArrayList<>();
        students.stream().reduce(new Student("default", 0, 0,null), (p1, p2) -> {
            if(p1.getAge() > p2.getAge()){
                return p1;
            }
            return null;
        });
        List<Person> names = students.stream().peek(s ->{
            s.setAge(18);
        }).collect(Collectors.toList());
    }
}
