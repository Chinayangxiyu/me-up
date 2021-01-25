package com.up.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2020-12-22 17:05
 */
public class Parent {

    private Parent child;


    public void tt(){
        child.privateMethod();
        System.out.println(child.getClass());
    }

    final public void finalMethod(String name) {
        System.out.println("finalMethod");
    }

    public void publicMethod() {
        System.out.println("publicMethod");
    }

    private void privateMethod() {
        System.out.println("privateMethod");
    }
}
