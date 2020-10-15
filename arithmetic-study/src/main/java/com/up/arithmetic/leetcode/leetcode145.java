package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class leetcode145 {


    public List<Integer> postorderTraversal(TreeNode root) {

        foreach(root);
        return result;
    }

    private List<Integer> result = new ArrayList<>();
    private void foreach(TreeNode root){
        if(root == null){
            return;
        }

        foreach(root.left);
        foreach(root.right);
        result.add(root.val);
    }
}
