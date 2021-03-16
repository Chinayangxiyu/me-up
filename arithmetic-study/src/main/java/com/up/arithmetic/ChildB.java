package com.up.cglib;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-01-25 22:30
 */
public class ChildB extends com.up.cglib.ParentA {



    @Override
    public void parentMethod2(){
        System.out.println("child parentMethod2");
        super.parentMethod2();
    }
}
