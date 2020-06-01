package com.up.arithmetic.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yxy
 * @date 2020/5/15 15:28
 * @description
 */
public class leetcode560 {

    public static void main(String[] args) {

//        int[] nums = new int[]{1,1,1};
//        int[] nums = new int[]{0,0,0,0,0,0,0,0,0,0};
//        System.out.println(subarraySum(nums, 2));

    }

    // 0特殊处理
    public static int subarraySum(int[] nums, int k) {

        int count = 0;
        int[] array = new int[nums.length];

        for(int i =0; i< nums.length; i++){
            for(int j = 0; j <=i; j++){
                array[j] = array[j] + nums[i];

                if(array[j] == k){
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    // 0特殊处理
    public static int subarraySum1(int[] nums, int k) {

        int count = 0;
        int base = 0;
        for(int i =0; i< nums.length; i++){
            for(int j = 0; j <=i; j++){

            }
        }

        return count;
    }
}
