package com.up.arithmetic.interview;

import java.util.Objects;


public class Solution0107 {

    public static void main(String[] args) {
        Solution0107 s = new Solution0107();


    }

    // ����1��ֱ����ת
    public void rotate(int[][] matrix) {


        // ����ת��������ֵ
        // ��ȡ��һ�е�ֵmatrix[0][0-n], ��ȡ���һ�е�ֵmatrix[0-n][n]
        // ��ȡ���һ�е�ֵmatrix[n][0-n],��ȡ��һ�е�ֵmatrix[0-n][n]
        // ��������Ľ��ۣ���ȡ����һ���ֵ��ֻ���������������ұ�����ֵ�����鳤��ϢϢ��ء�

        int i = 0;
        int j = matrix.length-1;
        int temp;
        for(;i < j; i++, j--){
            // ��һ�λ�ȡ������ֵ��
            for(int k = i, l = j; k < j && l >i; k++,l--){
                temp = matrix[i][k]; // matrix[i][k], i���䣬k����;��һ��
                matrix[i][k] = matrix[l][i]; // matrix[j-k][i], (j-k)�ݼ���i���㣻��һ��
                matrix[l][i] = matrix[j][l]; // matrix[j][l], j���䣬l�ݼ������һ��
                matrix[j][l] = matrix[k][j]; // matrix[k][i], k������j����,���һ��
                matrix[k][j] = temp;
            }
        }
    }

    // ����2���ȱ���������װ

}
