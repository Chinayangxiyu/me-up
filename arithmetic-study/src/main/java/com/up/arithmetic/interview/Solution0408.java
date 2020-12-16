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
 * ��Ʋ�ʵ��һ���㷨���ҳ���������ĳ�����ڵ�ĵ�һ����ͬ���ȡ�
 * ���ý������Ľڵ�洢����������ݽṹ�С�ע�⣺�ⲻһ���Ƕ���������
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
     * ������
     * 1��ÿ���ڵ�����ӽڵ㶼��һ�ö�������
     * 2��p��q���Էֲ���ͬһ�ö������У�Ҳ���Էֱ�ֲ�������������
     * 3���ݹ���� p��q�Ƿ�����ڵ�ǰ�ڵ������������
     * һ�������¼��������
     * ��1����ǰ�ڵ�����������null����������Ϊnull��˵��p��q������������
     * ��2����ǰ�ڵ�����������null����������Ϊnull������p��q������������
     * ��3�����������������Ϊnull����ǰ�ڵ����p��q������������Ƚڵ㡣
     *
     * 4��
     *
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        // ����Ҫ��:������������㣬��˵���ҵ���p��q
        if(root.val == p.val || root.val== q.val){
            return root;
        }

        // �ݹ������������Ƿ��ܴ���p��q����֮һ
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // �ݹ������������Ƿ��ܴ���p��q����֮һ
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // ���������������Ϊnull��˵��p��q�ֲ������������ڵ��У���ô��ǰ�ڵ��������Ĺ������Ƚڵ�
        if(left != null && right != null){
            return root;
        }

        // left��right��һ��Ϊnull����ô���ز���null�Ľڵ㣻
        // �ýڵ����Ϊ�ݹ�ص���"���"����ǵ�ǰ����˳���õ���p��q֮һ��
        // �ȵ�ǰ������Ϊnull��
        return left == null ? right : left;

    }

        /**
         * �ⷨ1���ֱ����p,q�����и��ڵ㣬Ȼ���ٽ��бȽϡ����ǲ��������⡿
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
