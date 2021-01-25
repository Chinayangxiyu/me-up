package com.up.arithmetic.leetcode;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2020-12-31 16:23
 */
public class leetcode300 {

    public static void main(String[] args) {

        int[] array = new int[]{10,9,2,5,3,7,101,18};
        leetcode300 l = new leetcode300();

        System.out.println(l.lengthOfLIS(array));
    }


    /**
     * f(n) = max(f(n-1)) + 1 && nums[n] > nums[n-1]
     *
     * 1、状态转移方程， 最长子序列 f(n) = max(f(n-1)) + (nums[n] > nums[n-1] ? 1 : 0)。
     * 2、创建一个一纬数组，存每个位置的最长的 递增子序列。
     */
    public int lengthOfLIS(int[] nums) {


       int[] dp = new int[nums.length];
        dp[0] = 1;
        int lasfSize = 1;
        //  遍历数组
       for(int i = 1; i < nums.length; i++){

           // 遍历获取数组每一个位置的最长递增子序列；既从0到i之间的最长子序列
           dp[i] = 1;
           // dp[i]还有临时缓存的作用
           for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
           }

           lasfSize = Math.max(lasfSize, dp[i]);
       }

       return dp[nums.length-1];
    }
}
