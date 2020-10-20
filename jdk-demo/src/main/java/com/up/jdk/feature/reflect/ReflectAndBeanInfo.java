package com.up.jdk.feature.reflect;

import org.omg.PortableInterceptor.INACTIVE;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

public class ReflectAndBeanInfo {


    public static void main(String[] args){

        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
            PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
            MethodDescriptor[] ms = beanInfo.getMethodDescriptors();
            Stream.of(ps).forEach(p -> {
//                p.getWriteMethod()
                System.out.println(p.getName());
            });

            Stream.of(ms).forEach(m -> {
                System.out.println(m.getName());
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}



class Student{

    private String sName;
    private Integer age;
    private Long higth;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}