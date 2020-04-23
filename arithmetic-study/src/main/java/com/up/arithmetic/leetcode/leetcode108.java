package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yxy
 * @date 2020/4/23 18:54
 * @description
 */
public class leetcode108 {

    //将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
    //
    //本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

    // 分析思路，二叉树的遍历方式分先根、中根、后根；根据中根和先根、后根中的一个可以推导出树的结构
    //
    // 根节点是 length/2 , 一级节点是 length/4
    public static void main(String[] args) {
        int[] nums = new int[]{1,4,8,9,14,16,19,25,32};
        TreeNode result = sortedArrayToBST(nums);

        List list = TreeNode.convertToArray(result, new ArrayList<>());
        list.forEach(o -> System.out.println(o));
    }


    public static TreeNode sortedArrayToBST(int[] nums) {

        return clac(nums, 0, nums.length-1);
    }

    public static TreeNode clac(int[] nums, int start, int end){

        if(end < start){
            return null;
        }
        if(start == end){
            return new TreeNode(nums[start]);
        }
        int index = (start + end + 1)/2;
        int value = nums[index];
        TreeNode node = new TreeNode(value);

        node.left = clac(nums, start, index-1);
        node.right = clac(nums, index+1, end);

        return node;
    }
}
