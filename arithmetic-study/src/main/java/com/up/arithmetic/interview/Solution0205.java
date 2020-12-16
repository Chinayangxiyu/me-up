package com.up.arithmetic.interview;


import com.up.arithmetic.leetcode.base.ListNode;

/**
 * ���������������ʾ��������ÿ���ڵ����һ����λ��
 * ��Щ��λ�Ƿ����ŵģ�Ҳ���Ǹ�λ���������ײ���
 * ��д������������������ͣ�����������ʽ���ؽ����
 */
public class Solution0205 {

    public static void main(String[] args) {
        Solution0205 s = new Solution0205();


    }

    /**
     * �ⷨһ���Ż���
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
         * �ⷨһ��
         * ��1���������������������ֵ������һ���µ�node,��Ҫע��������10ʱ���ǽ�λ��
         * ��2��������һ�����������������Ҫ������һδ������ɵ�������ʱ��Ҫע���λ "up"��ֵ
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
