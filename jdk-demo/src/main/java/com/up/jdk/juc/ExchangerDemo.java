package com.up.jdk.juc;

import java.util.concurrent.Exchanger;

/**
 * Exchanger
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();


        Thread t1 = new Thread(()->{
            String name1 = "thread1";
            int sign = 1;
            try {

                name1 = (String) exchanger.exchange(name1);
                System.out.println(sign + ":" + name1);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            String name = "thread2";
            int sign = 2;
            try {

                name = (String) exchanger.exchange(name);
                System.out.println(sign + ":" + name);

            }catch (Exception e){
                e.printStackTrace();
            }

        });

       t1.start();
       t2.start();
    }
}
