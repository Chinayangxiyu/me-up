package com.up.jdk.collection;

import java.util.*;

/**
 * @author yxy
 * @date 2020/4/22 21:26
 * @description
 */
public class MeSet {

    public static void main(String[] args) {

        HashSet set = new HashSet();
////
//        HashMap map = new HashMap();
//        Object obj = map.put("as", new Object());
//        Object obj1 = map.put("a", new String());
//        map.get("a");
//
////        System.out.println(obj);
////        System.out.println(obj1);
        boolean flag = set.add(new A());
        boolean flag2 = set.add(new A());
        set.remove(new A());
//
//        System.out.println(flag);
//        System.out.println(flag2);
//        System.out.println(set.remove("1"));

        TreeSet treeSet = new TreeSet();


        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("aa",12);


    }
}

class A{

    private String name;

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
