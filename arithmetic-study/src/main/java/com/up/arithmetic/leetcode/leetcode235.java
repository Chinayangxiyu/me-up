package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class leetcode235 {



    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);

        TreeNode l1 = new TreeNode(2);
        root.left = l1;

        TreeNode l1l = new TreeNode(0);
        l1.left = l1l;

        TreeNode l1r = new TreeNode(4);
        l1.right = l1r;

        TreeNode l1rl = new TreeNode(3);
        l1r.left = l1rl;

        TreeNode l1rr = new TreeNode(5);
        l1r.right = l1rr;

        TreeNode r1 = new TreeNode(8);
        root.right = r1;

        TreeNode r1l = new TreeNode(7);
        r1.left = r1l;

        TreeNode r1r = new TreeNode(9);
        r1.right = r1r;


        leetcode235 l235 = new leetcode235();
        TreeNode result = l235.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(4));

        System.out.println(result.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> listp = new ArrayList<>();

        foreach(root, p.val, listp);

        List<TreeNode> listq = new ArrayList<>();
        foreach(root, q.val, listq);
        for(int i = listp.size()-1, j=listq.size()-1; i >-1 && j> -1; i--,j--){

            if(listp.get(i).val != listq.get(j).val){
                return listp.get(i+1);
            }
        }

        return listp.size() > listq.size() ? listq.get(0) : listp.get(0);

    }




    private void foreach(TreeNode root, int val, List<TreeNode> list){

        if(root == null){
            return;
        }

        if(root.val == val){
            list.add(root);
            return ;
        }

        list.add(root);
        if(root.val > val){

            foreach(root.left, val, list);
            list.add(root);
        }else{

            foreach(root.right, val, list);
            list.add(root);
        }

    }
}
