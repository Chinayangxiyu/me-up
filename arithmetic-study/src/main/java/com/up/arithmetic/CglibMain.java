package com.up.arithmetic;

//import net.sf.cglib.proxy.Enhancer;

import com.up.cglib.ChildB;
import com.up.cglib.ParentA;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2020-12-22 17:10
 */
public class CglibMain {

    public static void main(String[] args) {

        ParentA parentA = new ParentA();
        ChildB b = new ChildB();
        parentA.p = b;

        b.parentMethod2();



//        Enhancer enhancer = new Enhancer();
//        //设置目标类的字节码文件
//        enhancer.setSuperclass(Parent.class);
//        //设置回调函数
//        enhancer.setCallback(new MyMethodInterceptor());
//
//        //这里的creat方法就是正式创建代理类
//        Parent proxyDog = (Parent)enhancer.create();
//
//
//
//        Parent p = new Parent();
//
//        Field field = p.getClass().getDeclaredFields()[0];
//        field.setAccessible(true);
//
//        try {
//            field.set(p, proxyDog);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        proxyDog.tt();
//        p.tt();
    }
}
