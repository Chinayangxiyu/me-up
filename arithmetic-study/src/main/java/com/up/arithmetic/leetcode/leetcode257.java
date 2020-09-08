package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class leetcode257 {


    public static void main(String[] args) {

        int i = 3;
        System.out.println(i);
        System.out.println((char)i);

        TreeNode node = new TreeNode(1);
        TreeNode lf = new TreeNode(2);
        TreeNode rg = new TreeNode(5);
        node.left = lf;
        node.right = rg;

        TreeNode rg1 = new TreeNode(3);
        lf.right = rg1;


        leetcode257 l = new leetcode257();
        l.binaryTreePaths(node);

    }


    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null){
            return null;
        }

        result = new ArrayList<>();
        tmp = new StringBuilder();
        find(root, 0);

        return result;
    }



    private String[] temp;
    private StringBuilder tmp;
    private List<String> result;
    TreeNode l;
    TreeNode r;
    private void find(TreeNode root, int index){
        if(root == null){
            return;
        }

        tmp.append('-');
        tmp.append('>');
        tmp.append(root.val);
        TreeNode l = root.left;
        TreeNode r = root.right;

        if(l == null && r == null){
            result.add(tmp.substring(2));
        }else{

            find(l, tmp.length());
            find(r, tmp.length());
        }

        tmp.delete(index, tmp.length());

    }
}
