package com.up.jdk.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yxy
 * @date 2020/4/22 23:19
 * @description
 */
public class MeMap {
    public static void main(String[] args) {
        LinkedHashMap<Cup, String> linkedHashMap = new LinkedHashMap<Cup, String>(10,0.75f, true);
        linkedHashMap.put(new Cup("红色", 10), "1");
        linkedHashMap.put(new Cup("白色", 10), "2");
        linkedHashMap.put(new Cup("黑色", 8), "3");
        linkedHashMap.put(new Cup("黑色", 6), "4");
        linkedHashMap.put(new Cup("蓝色", 10), "5");

        linkedHashMap.put(null, null);
        String index = linkedHashMap.get(new Cup("蓝色", 10));
        System.out.println(index);

        Set<Map.Entry<Cup,String>> set =  linkedHashMap.entrySet();
        set.forEach(e -> System.out.println(e.getValue() + e.getKey().getColour()));

        System.out.println("-------------");
        HashMap<Cup, String> hashMap = new HashMap<Cup, String>();
        hashMap.put(new Cup("红色", 10), "1");
        hashMap.put(new Cup("白色", 10), "2");
        hashMap.put(new Cup("黑色", 8), "3");
        hashMap.put(new Cup("黑色", 6), "4");
        hashMap.put(new Cup("蓝色", 10), "5");

        hashMap.put(null,null);
        Iterator<Map.Entry<Cup, String>> iterator = hashMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Cup, String> entry = iterator.next();
            System.out.println(entry.getValue() + entry.getKey().getColour());
        }
    }

    private void testTreeMap(){
        TreeMap treeMap = new TreeMap();
        treeMap.put("as",12);


    }

    private void testHashTable(){
        Cup cup1 = new Cup("红色", 7);
        Cup cup2 = new Cup("黑色", 8);
        Cup cup3 = new Cup("红色", 9);

        Hashtable<Cup, String> hashtable = new Hashtable();
        hashtable.put(cup1, "A");
        hashtable.put(cup2, "B");
        hashtable.put(cup3, "C");

        hashtable.put(null,null);

    }

    private void testWeakHashMap(){
        Cup cup1 = new Cup("紫色", 7);
        Cup cup2 = new Cup("白色", 8);
        Cup cup3 = new Cup("红色", 9);

        WeakHashMap<Cup, String> weakHashMap = new WeakHashMap();
        weakHashMap.put(cup1, "as");
        weakHashMap.put(cup2, "rr");
        weakHashMap.put(cup3, "ff");

        weakHashMap.remove(cup1);


    }

}

class Cup{
    private String colour;
    private int higth;

    public Cup(String colour, int higth) {
        this.colour = colour;
        this.higth = higth;
    }

    public String getColour() {
        return colour;
    }

    public Cup setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public int getHigth() {
        return higth;
    }

    public Cup setHigth(int higth) {
        this.higth = higth;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cup cup = (Cup) o;
        return higth == cup.higth &&
                Objects.equals(colour, cup.colour);
    }

    @Override
    public int hashCode() {

        return higth;
    }
}
