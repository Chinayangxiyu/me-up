package com.up.arithmetic.interview;

import com.up.arithmetic.leetcode.base.TreeNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Description
 *
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。
 * 不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树
 * @author xiyu
 * @date 2020-12-11 11:52
 */
public class Solution0408 {



    public static void main(String[] args) {

        System.out.println((int)'a' - 97);
        TreeNode root = new TreeNode(3);

        TreeNode l1 = new TreeNode(5);
        TreeNode r1 = new TreeNode(1);
        root.left = l1;
        root.right = r1;

        TreeNode l1l = new TreeNode(6);
        TreeNode l1r = new TreeNode(2);
        l1.left = l1l;
        l1.right = l1r;

        TreeNode l1rl = new TreeNode(8);
        TreeNode l1rr = new TreeNode(4);
        l1r.left = l1rl;
        l1r.right = l1rr;


        TreeNode r1l = new TreeNode(0);
        TreeNode r1r = new TreeNode(8);
        r1.left = r1l;
        r1.right = r1r;







        Solution0408 s = new Solution0408();
        TreeNode result = s.lowestCommonAncestor1(root,l1, l1rr );
        System.out.println("---------------");
        System.out.println(result.val);

    }


    /**
     * 分析：
     * 1、每个节点和其子节点都是一棵二叉树；
     * 2、p、q可以分布在同一棵二叉树中，也可以分别分布在左、右子树；
     * 3、递归查找 p、q是否存在于当前节点的左、右子树中
     * 一共分以下几类情况；
     * （1）当前节点左子树返回null，右子树不为null；说明p、q都在左子树，
     * （2）当前节点右子树返回null，左子树不为null，锁门p、q都在右子树，
     * （3）如果左、右子树都不为null，则当前节点就是p、q的最近公共祖先节点。
     *
     * 4、
     *
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        // 【重要】:如果该条件满足，则说明找到了p或q
        if(root.val == p.val || root.val== q.val){
            return root;
        }

        // 递归左子树，看是否能存在p、q其中之一
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // 递归右子树，看是否能存在p、q其中之一
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树都不为null，说明p、q分布在两个子树节点中；那么当前节点就是最近的公共祖先节点
        if(left != null && right != null){
            return root;
        }

        // left、right有一个为null；那么返回不是null的节点；
        // 该节点会作为递归回调的"标记"，标记当前遍历顺序拿到了p、q之一；
        // 既当前子树不为null，
        return left == null ? right : left;

    }

        /**
         * 解法1：分别求出p,q的所有父节点，然后再进行比较【但是不符合题意】
         * @param root
         * @param p
         * @param q
         * @return
         */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        temp = new ArrayList<>();
        each(root, p);
        List<TreeNode> plist = temp;

        temp = new ArrayList<>();
        flag = false;
        each(root, q);
        List<TreeNode> qlist = temp;


        int size = qlist.size() > plist.size() ? plist.size():qlist.size();
        if(size == 1){
            return qlist.get(0);
        }else{
            for(int i = 0; i < size; i++){
                if(qlist.get(i) != plist.get(i)){
                    return qlist.get(i-1);
                }
            }

        }

        return qlist.get(size-1);

    }

    List<TreeNode> temp = new ArrayList<>();
    boolean flag = false;

    public void each(TreeNode root, TreeNode c){
        if(root == null || flag){
            return;
        }

        if(root.val == c.val){
            flag = true;
        }
        temp.add(root);


        TreeNode l = root.left;
        each(l, c);

        TreeNode r = root.right;
        each(r, c);

        if(!flag){
            temp.remove(root);
        }

    }
}
