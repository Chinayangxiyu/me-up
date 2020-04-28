package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

/**
 * @author yxy
 * @date 2020/4/28 17:03
 * @description
 */
public class leetcode111 {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        root.left = left;

//        TreeNode right = new TreeNode(20);
//        root.right = right;
//
//        TreeNode rLeft = new TreeNode(15);
//        root.right.left =rLeft;
//        TreeNode rRight = new TreeNode(7);
//        root.right.right = rRight;


        System.out.println("----------------");
//        System.out.println(minDepth(root));
    }
    //给定一个二叉树，找出其最小深度。
    //最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
    //说明: 叶子节点是指没有子节点的节点。

    // 分析思路：
    // (1)遍历树每一个路径的深度；和当前最小深度比较，如果大于等于当前深度了，终止计算
    // 否则计算，直到子节点为null，然后将其深度更新为当前最小深度
    public int minDepth(TreeNode root) {

        if(root == null){
            return 0;
        }
        int depth = foreach(root, 0);
        return min;
    }

    public  int min = Integer.MAX_VALUE;
    public  int foreach(TreeNode node, int depth){

        if(node.left == null && node.right == null){
            ++depth;
            min = depth < min? depth : min;
            return depth;
        }

        ++depth;
        if(depth >= min ){
            return depth;
        }

        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;
        if(node.right != null){
            leftDepth =  foreach(node.right, depth);
        }
        if(node.left != null){
            rightDepth = foreach(node.left, depth);
        }

        int currentMin = leftDepth < rightDepth ?leftDepth : rightDepth;
        return currentMin < min ? currentMin : min;
    }
}
