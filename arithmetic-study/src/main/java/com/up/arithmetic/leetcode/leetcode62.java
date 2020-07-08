package com.up.arithmetic.leetcode;

import java.util.Arrays;

/**
 * 死磕动态规划01
 */
public class leetcode62 {



    public static void main(String[] args) {

        uniquePaths(4, 8);
    }


    // 使用动态规划
    // 1、到达目的地路径使用函数f(m,n)表示；
    // 2、因为机器人只能向下或向右移动，到达f(m,n)的上一个格子是f(m-1,n) 或f(m,n-1);
    // 3、所以达到（m,n）位置的路径数量，f(m,n) = f(m-1,n) + f(m,n-1)
    // 4、(0, n)和(m,0)是机器人运动的上、右边界；机器人到这个边界线上的路径数量f(0, n) = 1,f(m,0) = 1.

    /**
     * 方法1，空间复杂度 m * n
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {


        int[][] dp = new int[m][n];

        // 边界值赋值
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }

        for(int i = 0; i < m; i++){
            dp[m][0] = 1;
        }

        // 从起始位置开始，利用公式f(m,n) = f(m-1,n) + f(m,n-1)
        // 计算下一格子的概率
        for(int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }


        return dp[m][n];
    }

    // 方法2，优化空间复杂度
    // 分析：方法1的空间复杂度为m * n,相当于把每一个格子的值都记录一遍；
    // 但是对于dp(m-1,n)和dp(m,n-1)的值，每次参与计算之后就无用了；
    // 所以我们可以使用一个一维数组暂存当前计算的值，作为下一次计算的参数
    public static int uniquePaths1(int m, int n) {
        int[] cur = new int[n];
        // 填充初始化值
        Arrays.fill(cur,1);

        // 遍历每一行
        for(int i = 1; i < m; i++){
            // 行数据，
            for(int j = 1; j < n; j++){
                // cur[j-1]的值是方法1的dp[m][n-1]
                // cur[j]的值是方法1的dp[m-1][n]，
                cur[j] = cur[j-1] + cur[j];
            }
        }

        return cur[n-1];
    }

}
