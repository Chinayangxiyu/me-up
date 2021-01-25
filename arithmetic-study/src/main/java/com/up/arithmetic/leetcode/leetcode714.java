package com.up.arithmetic.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组?prices，其中第?i?个元素代表了第?i?天的股票价格 ；非负整数?fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
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
     * 解法一、贪心算法
     * （1）将交易费用fee和买入的价格绑定在一起；初始价格 cur = prices[0] + fee;
     * （2）如果后续的价格 prices[n] + fee < cur,说明有更低的买入点，直接替换cur = prices[n] + fee;
     * （3）如果后续的价格 prices[n] > cur,说明会有盈利产生；盈利total = prices[n] - cur;
     *     （3-1）计算万当前盈利，如果下一天继续涨，那么盈利 应该等于 prices[n + 1] - prices[n]，因为n 和 n+1算一笔交易不需要减除费用；
     *     （3-2）计算完盈利后，下一天如果跌了，如果跌幅超过了fee => prices[n] > prices[n+1] + fee，则需要重新初始化cur = prices[n+1] + fee;
     *          既然会将cur初始化变为prices[n+1] + fee；且prices[n+1] + fee < prices[n]，所以当前步骤直接将 cur = prices[n]也没问题，因为最终
     *          都会变为prices[i+1] + fee。
     *     （3-3）如果下一天跌了，跌幅没超过fee，既prices[n] < prices[n+1] + fee，则不会进行任何操作；
     *              继续遍历直到找到prices[n] > prices[n+1] + fee为止，进行（3-2）的替换。
     *              如果一直未找到满足条件的 prices[n] > prices[n+1] + fee；那么最终会出现两种情况
     *              1是后面的值都是(prices[n] -fee , prices[n])区间内，不会再有计算；
     *              2是后面会找到比prices[n] 大的值prices[x]，则需要 加上prices[x] - prices[n]的值。
     *
     *     所以cur 应该更新为 prices[n];
     *     总结：
     * （4）如果值在区间  prices[n] + fee > cur &&  prices[n] < cur
     *
     */


    /**
     * 解法一、贪心算法
     * （1）将交易的费用和买入的价格绑定在一起，初始价格 cur = prices[0] + fee;
     * （2）如果后续的价格 prices[n] + fee < cur,说明有更低的买入点，直接替换cur = prices[n] + fee;
     * （3）如果后续的价格 cur -fee < prices[n] < cur，则表明不可能有更低的买入点，因为手续费，也不可能有盈利；
     * （4）如果后续的价格 prices[n] > cur，则说明会产生盈利；total = prices[n] - cur,需要考虑 n的下一天是涨、是跌；
     *    （4-1）如果n+1天还是涨，那么n天和n+1天的交易应该算是同一笔交易，total =  prices[n+1] -  prices[n],此时复制cur =  prices[n];
     *    （4-2）如果n+1天跌了，而且跌的幅度超过了fee,既 prices[n+1] + fee <  prices[n]，则最终会赋值 cur = prices[n+1]，因为最终都会
     *    赋值为prices[n+1]，所以当前计算，将cur赋值为 prices[n]也只是暂时的。
     *    （4-3）如果n+1,n+2... x的值都处于(prices[n] - fee, prices[n])之间，则既没有盈利，也没有更低的买入点，所以不做操作。
     *
     * 【总结】：遇到比当前买入点更低的价格买入，遇到比买入点价格高的节点考虑卖出，具体是否卖出，需要
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