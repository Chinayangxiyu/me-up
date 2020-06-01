package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yxy
 * @date 2020/5/22 16:28
 * @description
 */
public class leetcode105 {

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        buildTree(preorder, inorder);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        return calc(preorder, inorder, 0, 0, inorder.length-1);

    }

    /*
     * @param preorder 先序数组
     * @param inorder 中序数组
     * @param rootIndex 当前节点在先序数组中的索引
     * @param startIndex 当前树元素，在中序数组中的起始索引（范围）
     * @param endIndex 在中序数组中的截止索引（范围）
     */
    public static TreeNode calc(int[] preorder, int[] inorder, int rootIndex, int startIndex, int endIndex) {

        // 终止
        if(endIndex < startIndex){
            return null;
        }
        // 构建当前节点
        TreeNode root = new TreeNode(preorder[rootIndex]);

        // 当前节点不存在子节点
        if(endIndex == startIndex){
            return root;
        }

        // 当前节点值在中序数组中的位置
        int midIndex = 0;
        for(int i = startIndex; i <= endIndex; i++){
            if(inorder[i] == preorder[rootIndex]){ // 根节点
                midIndex = i;
                break;
            }
        }

        // 存在左子树并构建
        if(midIndex > startIndex){
            // 左子树的根节点，在先序数组中的位置 rootIndex + 1
            TreeNode letfNode = calc( preorder,  inorder, rootIndex + 1,  startIndex ,midIndex-1);
            root.left = letfNode;
        }

        // 存在右子树并构建
        if(midIndex < endIndex){
            // 右子树的根节点，在先序数组中的位置 rootIndex + (midIndex - startIndex) + 1
            TreeNode rightNode = calc( preorder,  inorder, rootIndex + (midIndex - startIndex) + 1, midIndex+1, endIndex);
            root.right = rightNode;
        }

        return root;

    }
}
