package com.up.cglib;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-01-25 22:30
 */
public class ParentA {

    public ParentA p;

    public void parentMethod2(){
        System.getProperty("parent parentMethod2");
        p.method1();
    }

    private void method1(){
        System.out.println("age:" + 10);
    }
}
