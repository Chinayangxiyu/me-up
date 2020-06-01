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

    // 1����һ�� -> ���һ�У����һ�� ��>���һ�У����һ�� -> ��һ�У���һ�� ->��һ��
    // 2��ÿ����Ҫһ����ʱ��һά���黺��ֵ
    // 3������㴦������󣬴����ڲ㣬��ʱȥ����β ����Ԫ��
    // 4���ݹ�

    // ��ôһ�λ�ȡ
    public static void rotate(int[][] matrix) {

        int minIndex = 0;
        int maxIndex =  matrix.length-1;

        int tmp;
        for(; minIndex < maxIndex; minIndex++, maxIndex--){
            // ��ȡ��ǰ�����ε�һ��,���һ�� ����
                for(int i = minIndex,j = maxIndex; i < maxIndex && j> minIndex; i++,j--){
                    tmp = matrix[minIndex][i];
                    matrix[minIndex][i] = matrix[j][minIndex];
                    matrix[j][minIndex] = matrix[maxIndex][j];
                    matrix[maxIndex][j] = matrix[i][maxIndex];
                    matrix[i][maxIndex] = tmp;
                }
        }
    }
}
