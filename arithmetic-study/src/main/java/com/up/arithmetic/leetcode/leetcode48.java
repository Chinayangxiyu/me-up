package com.up.arithmetic.leetcode;

/**
 * @author yxy
 * @date 2020/6/1 16:38
 * @description
 */
public class leetcode48 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        rotate(matrix);
    }

    // 1、第一行 -> 最后一列；最后一列 —>最后一行；最后一行 -> 第一列；第一列 ->第一行
    // 2、每次需要一个临时的一维数组缓存值
    // 3、最外层处理结束后，处理内层，此时去除首尾 两个元素
    // 4、递归

    // 怎么一次获取
    public static void rotate(int[][] matrix) {

        int i = 0;
        int j =  matrix.length-2;

        for(; i < j; i++,j--){

//            int temp = matrix[i][matrix.length-1 - j];
//            matrix[i][matrix.length-1 - j] = matrix[matrix.length-1 - j][i];
//            matrix[matrix.length-1 - j][i] = matrix[j][matrix.length-1 - i];
//            matrix[j][matrix.length-1 - i] = matrix[matrix.length-1-i][j];
//            matrix[i][j] = temp;

            int temp = matrix[0][0];
            matrix[0][0] = matrix[matrix.length-1][0];

            matrix[i][matrix.length-1 - j] = matrix[matrix.length-1 - j][i];
            matrix[matrix.length-1 - j][i] = matrix[j][matrix.length-1 - i];
            matrix[j][matrix.length-1 - i] = matrix[matrix.length-1-i][j];
            matrix[i][j] = temp;




        }
    }
}
