package com.up.arithmetic.leetcode;

import java.util.Arrays;

/**
 * ����һ����������?prices�����е�?i?��Ԫ�ش����˵�?i?��Ĺ�Ʊ�۸� ���Ǹ�����?fee �����˽��׹�Ʊ���������á�
 * ��������޴ε���ɽ��ף�������ÿ�ʽ��׶���Ҫ�������ѡ�������Ѿ�������һ����Ʊ����������֮ǰ��Ͳ����ټ��������Ʊ�ˡ�
 * ���ػ����������ֵ��
 * ע�⣺�����һ�ʽ���ָ������в�������Ʊ���������̣�ÿ�ʽ�����ֻ��ҪΪ֧��һ�������ѡ�
 */
public class leetcode714 {


    public static void main(String[] args) {

        leetcode714 l = new leetcode714();
//        System.out.println(l.maxProfit(new int[]{1,3,2,8,4,9}, 2));
//        System.out.println(l.maxProfit(new int[]{4,5,2,4,3,3,1,2,5,4}, 1));
//        System.out.println(l.maxProfit(new int[]{1,3,2,8,4,9}, 2));
//        System.out.println(l.maxProfit(new int[]{1,3,7,5,10,3}, 3));
//        System.out.println(l.maxProfit(new int[]{9,8,7,1,2}, 3));
        System.out.println(l.maxProfit(new int[]{1,2,5,1,10}, 2));
    }



    /**
     * �ⷨһ��̰���㷨
     * ��1�������׷���fee������ļ۸����һ�𣻳�ʼ�۸� cur = prices[0] + fee;
     * ��2����������ļ۸� prices[n] + fee < cur,˵���и��͵�����㣬ֱ���滻cur = prices[n] + fee;
     * ��3����������ļ۸� prices[n] > cur,˵������ӯ��������ӯ��total = prices[n] - cur;
     *     ��3-1��������ǰӯ���������һ������ǣ���ôӯ�� Ӧ�õ��� prices[n + 1] - prices[n]����Ϊn �� n+1��һ�ʽ��ײ���Ҫ�������ã�
     *     ��3-2��������ӯ������һ��������ˣ��������������fee => prices[n] > prices[n+1] + fee������Ҫ���³�ʼ��cur = prices[n+1] + fee;
     *          ��Ȼ�Ὣcur��ʼ����Ϊprices[n+1] + fee����prices[n+1] + fee < prices[n]�����Ե�ǰ����ֱ�ӽ� cur = prices[n]Ҳû���⣬��Ϊ����
     *          �����Ϊprices[i+1] + fee��
     *     ��3-3�������һ����ˣ�����û����fee����prices[n] < prices[n+1] + fee���򲻻�����κβ�����
     *              ��������ֱ���ҵ�prices[n] > prices[n+1] + feeΪֹ�����У�3-2�����滻��
     *              ���һֱδ�ҵ����������� prices[n] > prices[n+1] + fee����ô���ջ�����������
     *              1�Ǻ����ֵ����(prices[n] -fee , prices[n])�����ڣ��������м��㣻
     *              2�Ǻ�����ҵ���prices[n] ���ֵprices[x]������Ҫ ����prices[x] - prices[n]��ֵ��
     *
     *     ����cur Ӧ�ø���Ϊ prices[n];
     *     �ܽ᣺
     * ��4�����ֵ������  prices[n] + fee > cur &&  prices[n] < cur
     *
     */


    /**
     * �ⷨһ��̰���㷨
     * ��1�������׵ķ��ú�����ļ۸����һ�𣬳�ʼ�۸� cur = prices[0] + fee;
     * ��2����������ļ۸� prices[n] + fee < cur,˵���и��͵�����㣬ֱ���滻cur = prices[n] + fee;
     * ��3����������ļ۸� cur -fee < prices[n] < cur��������������и��͵�����㣬��Ϊ�����ѣ�Ҳ��������ӯ����
     * ��4����������ļ۸� prices[n] > cur����˵�������ӯ����total = prices[n] - cur,��Ҫ���� n����һ�����ǡ��ǵ���
     *    ��4-1�����n+1�컹���ǣ���ôn���n+1��Ľ���Ӧ������ͬһ�ʽ��ף�total =  prices[n+1] -  prices[n],��ʱ����cur =  prices[n];
     *    ��4-2�����n+1����ˣ����ҵ��ķ��ȳ�����fee,�� prices[n+1] + fee <  prices[n]�������ջḳֵ cur = prices[n+1]����Ϊ���ն���
     *    ��ֵΪprices[n+1]�����Ե�ǰ���㣬��cur��ֵΪ prices[n]Ҳֻ����ʱ�ġ�
     *    ��4-3�����n+1,n+2... x��ֵ������(prices[n] - fee, prices[n])֮�䣬���û��ӯ����Ҳû�и��͵�����㣬���Բ���������
     *
     * ���ܽ᡿�������ȵ�ǰ�������͵ļ۸����룬�����������۸�ߵĽڵ㿼�������������Ƿ���������Ҫ
     */
    public int maxProfit(int[] prices, int fee) {
        int cur = prices[0] + fee;
        int total = 0;
        for (int i = 1; i < prices.length; i++){
            if(prices[i] + fee < cur ){
                cur = prices[i] + fee;
            }else if(prices[i] > cur){
                total += prices[i] - cur;
                cur = prices[i];
            }
        }
        return total;

    }

}