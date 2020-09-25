package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

public class leetcode106 {


    public static void main(String[] args) {
        leetcode106 l = new leetcode106();

        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        l.buildTree(inorder, postorder);
    }
        /**
         * 1、后序遍历，最后一个节点是root节点；
         * 2、拿到root节点，拆分中序遍历的左右子树；
         * 3、根据左子树找到后序遍历中对应的元素序列，后序遍历的最后一个元素是左子树的root；依次递归，直到元素长度为零
         * @param inorder
         * @param postorder
         * @return
         */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || inorder.length ==0){
            return null;
        }

        this.inorder = inorder;
        this.postorder = postorder;
        TreeNode temp = new TreeNode(0);

        getIndex(temp, 0, inorder.length-1, postorder.length-1);
        return temp;
    }




    int[] postorder;
    int[] inorder;
    private void getIndex(TreeNode root, int leftIndex, int rightIndex, int rootIndex){


        // 创建一个
        root.val = postorder[rootIndex];


        // 获取后序遍历数组中最后一个元素，该元素是当前树的根节点

        // 当前中序遍历数组的长度
        // 遍历中序排列，比较每一个元素的值，如果和rootValue相同，则rootValue左边的元素是左子树，rootValue右变的元素构成右子树
        // ilindex，irindex是当前树在中序遍历中的左右边界值
        for(int i = leftIndex; i <=rightIndex; i++ ){
            // 遍历到符合结果的值
            if(inorder[i] == postorder[rootIndex]){
                // 满足条件肯定存在左子节点
                if(leftIndex <= i-1){
                    root.left = new TreeNode(0);
                    getIndex(root.left, leftIndex, i-1, rootIndex - rightIndex + i - 1);
                }

                // 满足条件，肯定存在右子节点
                if(i+1 <= rightIndex){
                    root.right = new TreeNode(0);
                    getIndex(root.right, i+1, rightIndex, rootIndex - 1);

                }
                break;
            }
        }

    }



}
