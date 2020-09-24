package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.*;

public class leetcode501 {



    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);

        TreeNode l1 = new TreeNode(2);
        root.left = l1;
        TreeNode l2 = new TreeNode(0);
        l1.left= l2;

        TreeNode r1 = new TreeNode(4);
        l1.right = r1;

        TreeNode l3 = new TreeNode(2);
        r1.left =l3;

        TreeNode r6 = new TreeNode(6);
        r1.right = r6;





        TreeNode r8 = new TreeNode(8);
        root.right = r8;

        TreeNode l7 = new TreeNode(7);
        r8.left = l7;

        TreeNode r9 = new TreeNode(9);
        r8.right = r9;



        leetcode501 l = new leetcode501();
        l.findMode(root);


    }
    /**
     * 遍历二叉树
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if(root == null){
            return new int[0];
        }

        foreach(root);
        if(curCount == maxCount){
            answer.add(cur);
            cur = root.val;

        }else if(curCount > maxCount){
            answer.clear();
            answer.add(cur);
            cur = root.val;
            maxCount = curCount;
        }

        int[] result = new int[answer.size()];
        for(int i = 0; i< answer.size(); i++){
            result[i] = answer.get(i);
        }
        return result;
    }



    List<Integer> answer = new ArrayList<Integer>();
    TreeNode l1;
    TreeNode r1;
    int cur;
    int curCount ;
    int maxCount ;
    public void foreach(TreeNode root){

        if(root == null){
            return;}


        l1 = root.left;
        foreach(l1);


        if(cur == root.val){
            curCount += 1;
        }else{
            if(curCount == maxCount){
                answer.add(cur);

            }else if(curCount > maxCount){
                answer.clear();
                answer.add(cur);
                maxCount = curCount;
            }
            cur = root.val;
            curCount =1;
        }


        r1 = root.right;
        foreach(r1);

    }




}
