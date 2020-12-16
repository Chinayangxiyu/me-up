package com.up.arithmetic.interview;


import com.up.arithmetic.leetcode.base.ListNode;

import java.util.Arrays;

/**
 *��д������ x Ϊ��׼�ָ�����ʹ������С�� x �Ľڵ����ڴ��ڻ���� x �Ľڵ�֮ǰ����������а��� x��x ֻ�������С�� x ��Ԫ��֮��(������ʾ)��
 * �ָ�Ԫ�� x ֻ�账�ڡ��Ұ벿�֡����ɣ��䲻��Ҫ����������������֮�䡣
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
     * ������
     * 1�������������ֵ��x�󣬽�ֵ���浽һ����ʱ����temp�У����ֵ��xС����ֵ���浽һ����ʱ����before�У�
     * 2��Ϊ�����ý�ȡֵ��������ʱ������Ҫʹ��һ�� ��ʱͷ�ڵ㣬���������ȡ������Ҫ������
     * 3�����ƴ�������ʱ����Ҫע��߽�ֵ�Ĵ���
     */
    public ListNode partition(ListNode head, int x) {

        // ֵ >= x�ĺ�������
        ListNode after = new ListNode(0);
        ListNode aftercur = after;

        // ֵ <= x��ǰ�������
        ListNode before = new ListNode(0);
        ListNode beforeCur = before;
        while (head != null){

            // ֵ < x,��headƴ�ӵ�before������
            if(head.val < x){
                beforeCur.next = head;
                beforeCur = beforeCur.next ;

                // ֵ >= x����headƴ�ӵ� temp��
            }else {
                aftercur.next = head;
                aftercur = aftercur.next;
            }
            head = head.next;
        }

        // ���߽紦��1�������Ҫ�����ر����õ������һ���ڵ�� next��Ϊnull�����⻷��
        aftercur.next = null;
        ListNode a = before.next;
        ListNode b = after.next;
        // ���߽紦��2�������ǰ�������Ϊ�գ���ֱ�ӷ��غ���
        if(a == null){
            return b;
        }
        beforeCur.next = b;
        return a;
    }
}
