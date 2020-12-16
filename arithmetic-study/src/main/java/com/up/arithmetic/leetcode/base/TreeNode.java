package com.up.arithmetic.leetcode.base;

import java.util.List;

/**
 * @author yxy
 * @date 2020/4/23 21:09
 * @description
 */
public class TreeNode {
    public int val;
    public  TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }



    /**
     * @description: 二叉树中根遍历，递归实现
     */
    public static List<Integer> convertToArray(TreeNode tree, List<Integer> list){

        if(tree != null && tree.left != null){
            convertToArray(tree.left, list);
        }
        list.add(tree.val);

        if(tree != null && tree.right != null){
            convertToArray(tree.right, list);
        }

        return list;
    }

 }
