package com.up.arithmetic.leetcode.base;

/**
 * @author yxy
 * @date 2020/6/2 16:19
 * @description
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }


    public static ListNode getNodes(int[] array){
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for(int i = 0; i < array.length; i++){
            temp.next = new ListNode(array[i]);
            temp = temp.next;
        }

        return head.next;
    }
}
