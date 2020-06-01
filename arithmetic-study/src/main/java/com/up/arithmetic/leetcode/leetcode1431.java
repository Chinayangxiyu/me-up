package com.up.arithmetic.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @author yxy
 * @date 2020/6/1 16:23
 * @description
 */
public class leetcode1431 {

    public static void main(String[] args) {
        boolean[] result = new boolean[10];
        for(int i = 0; i< 10; i++){
            System.out.println(result[i]);
        }
    }


    // 1、先求出最大的值
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        Boolean[] result = new Boolean[candies.length];
        int max = 0;
        for(int i = 0; i< candies.length; i++){
            max = candies[i] > max ? candies[i]:max;
        }

        for(int i = 0; i< candies.length; i++){
            if(candies[i] + extraCandies >= max){
                result[i] = true;
            }else {
                result[i] = false;
            }
        }
        return Arrays.asList(result);
    }

}
