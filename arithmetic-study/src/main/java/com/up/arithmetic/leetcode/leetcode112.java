package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

/**
 * @author yxy
 * @date 2020/5/15 18:21
 * @description
 */
public class leetcode112 {

    public static void main(String[] args) {

    }


    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }
        int val = sum - root.val;

        if (val == 0 && root.left == null && root.right ==null) {
            return true;
        }

        if (hasPathSum(root.left, val)) {
            return true;
        }

        if (hasPathSum(root.right, val)) {
            return true;
        }


        return false;
}


}
