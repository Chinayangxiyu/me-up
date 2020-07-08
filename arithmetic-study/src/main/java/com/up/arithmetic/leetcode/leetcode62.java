package com.up.arithmetic.leetcode;

import java.util.Arrays;

/**
 * ���Ķ�̬�滮01
 */
public class leetcode62 {



    public static void main(String[] args) {

        uniquePaths(4, 8);
    }


    // ʹ�ö�̬�滮
    // 1������Ŀ�ĵ�·��ʹ�ú���f(m,n)��ʾ��
    // 2����Ϊ������ֻ�����»������ƶ�������f(m,n)����һ��������f(m-1,n) ��f(m,n-1);
    // 3�����Դﵽ��m,n��λ�õ�·��������f(m,n) = f(m-1,n) + f(m,n-1)
    // 4��(0, n)��(m,0)�ǻ������˶����ϡ��ұ߽磻�����˵�����߽����ϵ�·������f(0, n) = 1,f(m,0) = 1.

    /**
     * ����1���ռ临�Ӷ� m * n
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {


        int[][] dp = new int[m][n];

        // �߽�ֵ��ֵ
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }

        for(int i = 0; i < m; i++){
            dp[m][0] = 1;
        }

        // ����ʼλ�ÿ�ʼ�����ù�ʽf(m,n) = f(m-1,n) + f(m,n-1)
        // ������һ���ӵĸ���
        for(int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }


        return dp[m][n];
    }

    // ����2���Ż��ռ临�Ӷ�
    // ����������1�Ŀռ临�Ӷ�Ϊm * n,�൱�ڰ�ÿһ�����ӵ�ֵ����¼һ�飻
    // ���Ƕ���dp(m-1,n)��dp(m,n-1)��ֵ��ÿ�β������֮��������ˣ�
    // �������ǿ���ʹ��һ��һά�����ݴ浱ǰ�����ֵ����Ϊ��һ�μ���Ĳ���
    public static int uniquePaths1(int m, int n) {
        int[] cur = new int[n];
        // ����ʼ��ֵ
        Arrays.fill(cur,1);

        // ����ÿһ��
        for(int i = 1; i < m; i++){
            // �����ݣ�
            for(int j = 1; j < n; j++){
                // cur[j-1]��ֵ�Ƿ���1��dp[m][n-1]
                // cur[j]��ֵ�Ƿ���1��dp[m-1][n]��
                cur[j] = cur[j-1] + cur[j];
            }
        }

        return cur[n-1];
    }

}
