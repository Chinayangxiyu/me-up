package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.ListNode;

/**
 * @author yxy
 * @date 2020/6/2 16:20
 * @description
 */
public class leetcode2 {
    public static void main(String[] args) {

    }

    // 1¡¢ÅÐ¶ÏÁ´±íÊÇ·ñ»·Á´
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode result = new ListNode(-1);
        ListNode current = result;
        int tmp1 = 0;
        int tmp2 = 0;
        int last = 0;
        while(l1 != null || l2 != null || last != 0){
            tmp1 = l1 != null ? l1.val : 0;
            tmp2 = l2 != null ? l2.val : 0;

            if(tmp1 + tmp2 + last>= 10){
                current.next = new ListNode((tmp1 + tmp2 + last) -10);
                last = 1;
            }else {
                current.next = new ListNode(tmp1 + tmp2 + last);
                last = 0;
            }

            tmp1 = 0;
            tmp2 = 0;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            current = current.next;
        }

        return result.next;
    }
}
