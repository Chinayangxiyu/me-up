package com.up.arithmetic.leetcode;

/**
 * @author yxy
 * @date 2020/5/1 16:33
 * @description
 */
public class leetcode122 {

    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4,5};
        maxProfit(array);
    }

    // 在低点买入，高点卖出；
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }

        int buyIndex = -1;
        int profit = 0;

        for(int i = 1; i < prices.length; i++){
            // 股票上升
            if(prices[i] > prices[i-1] && buyIndex == -1){
                buyIndex = i-1;
            }

            // 开始下跌，卖出
            if(prices[i] < prices[i-1] && buyIndex != -1){
                profit += (prices[i-1] - prices[buyIndex]);
                buyIndex = -1;
            }


        }

        if(buyIndex != -1){
            profit += (prices[prices.length-1] - prices[buyIndex]);
        }

        return profit;

    }
}
