package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yxy
 * @date 2020/4/24 14:17
 * @description
 */
public class leetcode110 {

    /*给定一个二叉树，判断它是否是高度平衡的二叉树。
    本题中，一棵高度平衡二叉树定义为：
    一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
    */

    // 什么是树的高度，什么是树的深度
    // 高度是当前节点到叶子节点最长的距离，既从下往上； 深度是指根节点到当前节点的距离。
    // 1、高度差不超过1，需要拿到每个节点的左右子树的高度差进行比较；
    // 2、遍历二叉树，获取左右子树的高度
    public static void main(String[] args) {


    }

    public static boolean isBalanced(TreeNode root) {
        int[] array = new int[]{0};
        comparetHigth(root, 0, array);
        return (array[0]) > 1 ? false : true;
    }



    // array[0]保存目前树的最大深度，array[1]保存最小深度
    public static int comparetHigth(TreeNode node,  int currentHigth, int[] array){

        if(node == null){
            return currentHigth;
        }
        currentHigth++;
        TreeNode leftNode = node.left;
        int leftHigth = comparetHigth(leftNode,currentHigth,  array);

        TreeNode rightNode = node.right;
        int rightHigth = comparetHigth(rightNode,currentHigth, array);

        if(Math.abs(leftHigth - rightHigth) > array[0]){
            array[0] = Math.abs(leftHigth - rightHigth);
        }



        return leftHigth > rightHigth ? leftHigth : rightHigth;
    }










        // array[0]保存目前树的最大深度，array[1]保存最小深度
    public static int[] getHigth(TreeNode node,  int currentHigth, int[] array){

        // 当前节点为null，既这条路径结束，既获取了这条路径的深度；
        // 将该深度和array中保存的深度比较，如果大于目前保存的最大深度
        // 或者小于目前保存的最小深度，执行替换
        if(node == null){
            if(array[0] < currentHigth || array[0]==0){
                array[0]= currentHigth;
            }

            if(array[1] > currentHigth || array[1]==0){
                array[1]= currentHigth;
            }
            return array;
        }

        currentHigth++;

        TreeNode leftNode = node.left;
        getHigth(leftNode,currentHigth,  array);

        TreeNode rightNode = node.right;
        getHigth(rightNode,currentHigth, array);
        return array;
    }
}
