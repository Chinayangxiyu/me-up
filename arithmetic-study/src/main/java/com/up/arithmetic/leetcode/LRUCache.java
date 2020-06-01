package com.up.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

/**
 * @author yxy
 * @date 2020/5/25 20:33
 * @description
 */
public class LRUCache {

    public static void main(String[] args) {
//        LinkedHashMap map = new LinkedHashMap(10,0.75F,true);
//        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        LRUCache cache = new LRUCache( 1/* 缓存容量 */ );
        cache.put(2,1);
        System.out.println(cache.get(2));

    }
    private   HashMap<Integer, Node> cup = null;
    private  Integer capacity = 0;
    private  Integer position = 0;
    private  Node header = null, tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        header = new Node();
        tail = new Node();
        header.next = tail;
        tail.pre = header;
        cup = new HashMap<>();


    }

    public int get(int key) {
        Node cur = cup.get(key);
        if(cur == null){
            return -1;
        }
        // 存在，需要将key对应的Node添加到链表头部;
        removeCurNode(cur);
        setHead(cur);

        return cur.value;
    }

    // 移除链表中的节点
    private void removeCurNode(Node cur){
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }

    // 设置头结点
    private void setHead(Node cur){
        cur.next = header.next;
        cur.next.pre = cur;
        cur.pre = header;
        header.next = cur;
    }

    public void put(int key, int value) {
        Node old = cup.get(key);
        if(old == null){ // 不为空
            Node cur = new Node(key, value);
            cup.put(key, cur);
            setHead(cur);
            if(position >= capacity){
                // 容量不够移除尾节点
                Node lastNode = tail.pre;
                cup.remove(lastNode.key);
                removeCurNode(lastNode);
                return;
            }
            position++;

        }else{
            old.value = value;
            removeCurNode(old);
            setHead(old);
        }
    }


    public static final class Node{

        public Integer key;
        public Integer value;
        public Node next;
        public Node pre;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }


}

