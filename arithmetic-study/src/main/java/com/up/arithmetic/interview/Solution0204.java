package com.up.arithmetic.interview;


import com.up.arithmetic.leetcode.base.ListNode;

import java.util.Arrays;

/**
 *编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。
 * 分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 */
public class Solution0204 {

    public static void main(String[] args) {
        Solution0204 s = new Solution0204();

//        ListNode node = getNodes(new int[]{3,5,8,5,10,2,1});
        ListNode node = getNodes(new int[]{1});
        ListNode result = s.partition(node, 0);
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }


    }

    public static ListNode getNodes(int[] array){
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for(int i = 0; i < array.length; i++){
            temp.next = new ListNode(array[i]);
            temp = temp.next;
        }

        return head.next;
    }


    /**
     * 分析：
     * 1、遍历链表，如果值比x大，将值缓存到一个临时链表temp中；如果值比x小，则将值缓存到一个临时链表before中；
     * 2、为了最后好截取值，声明临时链表需要使用一个 临时头节点，方便后续截取真正需要的链表。
     * 3、最后拼接链表的时候需要注意边界值的处理
     */
    public ListNode partition(ListNode head, int x) {

        // 值 >= x的后半截链表
        ListNode after = new ListNode(0);
        ListNode aftercur = after;

        // 值 <= x的前半截链表
        ListNode before = new ListNode(0);
        ListNode beforeCur = before;
        while (head != null){

            // 值 < x,将head拼接到before链表上
            if(head.val < x){
                beforeCur.next = head;
                beforeCur = beforeCur.next ;

                // 值 >= x，将head拼接到 temp上
            }else {
                aftercur.next = head;
                aftercur = aftercur.next;
            }
            head = head.next;
        }

        // 【边界处理1】：最后要将后半截遍历拿到的最后一个节点的 next置为null，避免环链
        aftercur.next = null;
        ListNode a = before.next;
        ListNode b = after.next;
        // 【边界处理2】：如果前半截链表为空，则直接返回后半截
        if(a == null){
            return b;
        }
        beforeCur.next = b;
        return a;
    }
}
