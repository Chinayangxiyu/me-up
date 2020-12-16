package com.up.arithmetic.interview;

import java.util.Objects;


public class Solution0107 {

    public static void main(String[] args) {
        Solution0107 s = new Solution0107();


    }

    // 方法1、直接旋转
    public void rotate(int[][] matrix) {


        // 先旋转最外层的数值
        // 获取第一行的值matrix[0][0-n], 获取最后一列的值matrix[0-n][n]
        // 获取最后一行的值matrix[n][0-n],获取第一列的值matrix[0-n][n]
        // 分析上面的结论，获取最外一层的值，只有两个变量，并且变量的值和数组长度息息相关。

        int i = 0;
        int j = matrix.length-1;
        int temp;
        for(;i < j; i++, j--){
            // 第一次获取最外层的值。
            for(int k = i, l = j; k < j && l >i; k++,l--){
                temp = matrix[i][k]; // matrix[i][k], i不变，k递增;第一行
                matrix[i][k] = matrix[l][i]; // matrix[j-k][i], (j-k)递减，i不便；第一列
                matrix[l][i] = matrix[j][l]; // matrix[j][l], j不变，l递减，最后一行
                matrix[j][l] = matrix[k][j]; // matrix[k][i], k递增，j不便,最后一列
                matrix[k][j] = temp;
            }
        }
    }

    // 方法2、先遍历，再组装

}
