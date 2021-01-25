package com.up.arithmetic.interview;


import com.up.arithmetic.leetcode.base.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ���������������ʾ��������ÿ���ڵ����һ����λ��
 * ��Щ��λ�Ƿ����ŵģ�Ҳ���Ǹ�λ���������ײ���
 * ��д������������������ͣ�����������ʽ���ؽ����
 */
public class Solution0208 {

    public static void main(String[] args) {
        Solution0208 s = new Solution0208();

        ListNode node =  new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new  ListNode(-4);
        node.next.next.next.next = node.next;

        System.out.println(s.detectCycle(node) .val);

    }

    public ListNode detectCycle1(ListNode head) {

        ListNode one = head;
        ListNode two = head;

        while(one != null && two != null && two.next != null){

            if(one == two){
                break;
            }else{
                one = one.next;
                two = two.next.next;
            }
        }

        if(two == null){
            return new ListNode(-1);
        }

        return null;
    }


    /**
     * ʹ��Map
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        Map<Integer, Integer> temp = new HashMap<>();
        int i = 0;
        while (head != null && !temp.keySet().contains(head.val)){
            temp.put(head.val, i);
            i++;
            head = head.next;
        }

        if(head == null){
            return new ListNode(-1);
        }


        return new ListNode(temp.get(head.val));
    }
}
