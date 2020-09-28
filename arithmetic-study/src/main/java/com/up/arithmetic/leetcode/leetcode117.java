package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class leetcode117 {


    public static void main(String[] args) {
        leetcode117 l = new leetcode117();

        Node root = new Node(1);

        Node l1 = new Node(2);

        root.left = l1;

        Node l1l = new Node(4);
        l1.left = l1l;

        Node l1r = new Node(5);
        l1.right = l1r;

        Node r1 = new Node(3);
        root.right = r1;

        Node r1r = new Node(7);
        r1.right = r1r;

        l.connect(root);
    }

    /**
     * 将当前节点的next指向右侧节点，右侧节点不是当前节点的right，是和他平级的右侧节点。
     * 广度遍历
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root == null){
            return null;
        }

        Queue<Node> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            Node last = null;

            for(int i = 1; i <=size; i++){
                Node n = queue.poll();
                if(n.left != null){
                    queue.offer(n.left);
                }

                if(n.right != null){
                    queue.offer(n.right);
                }

                if(i != 1){
                    last.next = n;

                }

                last = n;


            }
        }

        return root;
    }


}
