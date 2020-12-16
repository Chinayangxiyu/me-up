package com.up.arithmetic.interview;


import com.up.arithmetic.leetcode.base.ListNode;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 */
public class Solution0205 {

    public static void main(String[] args) {
        Solution0205 s = new Solution0205();


    }

    /**
     * 解法一（优化）
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        ListNode temp = new ListNode(0);
        ListNode cur = temp;
        int up = 0;
        int curVal;
        while(l1 != null || l2 != null){

            curVal = (l1 != null ? l1.val :0) + (l2 != null ? l2.val : 0) + up;
            up = 0;
            if(curVal < 10){
                ListNode node = new ListNode(curVal);
                cur.next = node;
                cur = node;
            }else{
                curVal %= 10;
                up = 1;
                ListNode node = new ListNode(curVal);
                cur.next = node;
                cur = node;
            }

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return temp.next;
    }



        /**
         * 解法一、
         * （1）遍历两个链表，相加两个值，生成一个新的node,需要注意结果大于10时考虑进位。
         * （2）当其中一个链表遍历结束后，需要处理另一未遍历完成的链表，此时需要注意进位 "up"的值
         * @param l1
         * @param l2
         * @return
         */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode temp = new ListNode(0);
        ListNode cur = temp;
        int up = 0;
        int curVal;
        while (l1 != null && l2 != null){

            curVal = l1.val + l2.val + up;
            up = 0;
            if(curVal < 10){
                ListNode node = new ListNode(curVal);
                cur.next = node;
                cur = node;
            }else{
                curVal %= 10;
                up = 1;
                ListNode node = new ListNode(curVal);
                cur.next = node;
                cur = node;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode after = l1 == null ? l2 :l1;


        while (after != null){
            curVal = after.val + up;
            up = 0;
            if(curVal < 10){
                ListNode node = new ListNode(curVal);
                cur.next = node;
                cur = node;
            }else{
                curVal %= 10;
                up = 1;
                ListNode node = new ListNode(curVal);
                cur.next = node;
                cur = node;
            }

            after = after.next;
        }

        if (up == 1){
            ListNode node = new ListNode(1);
            cur.next = node;

        }

        return temp.next;

    }
}
