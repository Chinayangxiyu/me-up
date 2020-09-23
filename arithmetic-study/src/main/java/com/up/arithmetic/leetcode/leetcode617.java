package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

public class leetcode617 {


    /**
     * 遍历，遍历的过程中将每个节点的值相加
     * 直到两个节点都为null，结束遍历
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if(t1 == null || t2 == null){
            return t1 == null ?  t2 : t1;
        }


        foreach(t1, t2, t1, true);

        return t1;
    }


    private static TreeNode l1;
    private static TreeNode l2;
    private static TreeNode r1;
    private static TreeNode r2;
    private void foreach(TreeNode t1, TreeNode t2, TreeNode root, boolean direction){


        if(t1 != null && t2 != null){
            t1.val += t2.val;


            l1 = t1.left;
            l2 = t2.left;
            foreach(l1, l2, t1, true);

            r1 = t1.right;
            r2 = t2.right;
            foreach(r1, r2, t1, false);


        }else if(t1 == null && t2 != null){
            TreeNode n = new TreeNode(t2.val);

            if(direction){
                root.left = n;
            }else {
                root.right = n;
            }

            l2 = t2.left;
            foreach(null, l2, n, true);

            r2 = t2.right;
            foreach(null, r2, n, false);

        }


    }
}
