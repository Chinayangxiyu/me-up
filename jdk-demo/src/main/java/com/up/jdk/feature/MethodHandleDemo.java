package com.up.jdk.feature;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import static java.lang.invoke.MethodHandles.lookup;

public class MethodHandleDemo {

    public static void main(String[] args) throws Exception, Throwable {
        Person p = new Person();

        // 1、虚方法方法句柄使用
        MethodType virtualType = MethodType.methodType(int.class, String.class);

        MethodHandle virtualMethod = lookup()
                .findVirtual(p.getClass(), "virtualMethod", virtualType) // 查找虚方法
                .bindTo(p); // 虚方法是实例方法，需要绑定实例；
        virtualMethod.invoke("测试 静态方法句柄调用");

        // invokeExact表示精确调用，不会对参数和返回值执行类型转换；
        // 因为virtualMethod方法的参数是String类型；所以如下调用会报错
//         virtualMethod.invokeExact(11);

        // 2、静态方法，方法句柄使用
        MethodType staticType = MethodType.methodType(void.class);
        MethodHandle statucHandle = lookup().
                findStatic(Person.class, "staticMethod", staticType);// 查找静态方法
        statucHandle.invokeExact();

    }
}


class Person{


    public int virtualMethod(String n){
        System.out.println("this is a virtual method；param=" + n );
        return 100;
    }

    public static final void staticMethod(){
        System.out.println("this a static method");
    }
}

