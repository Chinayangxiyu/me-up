package com.up.book;

public class StackModel {


    public void method1(Student s1){
        s1.setAge(12);
        s1 = new Student();
    }

}

class Student{

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
