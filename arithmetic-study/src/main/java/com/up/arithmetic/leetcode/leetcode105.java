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
     * @param preorder ��������
     * @param inorder ��������
     * @param rootIndex ��ǰ�ڵ������������е�����
     * @param startIndex ��ǰ��Ԫ�أ������������е���ʼ��������Χ��
     * @param endIndex �����������еĽ�ֹ��������Χ��
     */
    public static TreeNode calc(int[] preorder, int[] inorder, int rootIndex, int startIndex, int endIndex) {

        // ��ֹ
        if(endIndex < startIndex){
            return null;
        }
        // ������ǰ�ڵ�
        TreeNode root = new TreeNode(preorder[rootIndex]);

        // ��ǰ�ڵ㲻�����ӽڵ�
        if(endIndex == startIndex){
            return root;
        }

        // ��ǰ�ڵ�ֵ�����������е�λ��
        int midIndex = 0;
        for(int i = startIndex; i <= endIndex; i++){
            if(inorder[i] == preorder[rootIndex]){ // ���ڵ�
                midIndex = i;
                break;
            }
        }

        // ����������������
        if(midIndex > startIndex){
            // �������ĸ��ڵ㣬�����������е�λ�� rootIndex + 1
            TreeNode letfNode = calc( preorder,  inorder, rootIndex + 1,  startIndex ,midIndex-1);
            root.left = letfNode;
        }

        // ����������������
        if(midIndex < endIndex){
            // �������ĸ��ڵ㣬�����������е�λ�� rootIndex + (midIndex - startIndex) + 1
            TreeNode rightNode = calc( preorder,  inorder, rootIndex + (midIndex - startIndex) + 1, midIndex+1, endIndex);
            root.right = rightNode;
        }

        return root;

    }
}
