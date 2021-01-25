package com.up.cglib;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2020-12-22 17:10
 */
public class CglibMain {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Parent.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的creat方法就是正式创建代理类
        Parent proxyDog = (Parent)enhancer.create();



        Parent p = new Parent();

        Field field = p.getClass().getDeclaredFields()[0];
        field.setAccessible(true);

        try {
            field.set(p, proxyDog);
        }catch (Exception e){
            e.printStackTrace();
        }

        proxyDog.tt();
        p.tt();
    }
}
