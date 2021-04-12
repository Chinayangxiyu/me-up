package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.HashSet;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-04-07 21:03
 */
public class Test {


    public static void main(String[] args) {

    }

    public boolean method1(Node root){

        HashSet<Node> container = new HashSet<>();
        Node cur = root;
        while (cur != null){
            if(container.contains(cur)){
                return true;
            }
            container.add(cur);
            cur = cur.next;

        }

        return false;

    }

    public boolean method2(Node root){

        if(root == null || root.next ==null){
            return false;
        }

        Node one = root;
        Node two = root.next;
        while (one != null && two != null){

            if(one == two){
                return true;
            }

            one = one.next;
            if(two.next == null){
                return false;
            }
            two = two.next.next;

        }

        return false;

    }
}

class Node{

    Node next;
}
