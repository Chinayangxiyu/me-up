package com.up.arithmetic.interview;


import java.util.*;

public class Solution0108 {

    public static void main(String[] args) {
        Solution0108 s = new Solution0108();


    }



    /**
     * ��������
     * ��1��������ʱ������ҵ��㣬�򽫵�ǰֵ������λ�û��棻
     * ��2�����������������ǰֵ�����꣬λ�ڻ���������λ�ã���ֵ���޸�Ϊ�㡣
     * ��3�������ǰλ��Ϊ�㣬����Ҫ����ȥ����Ӧλ�ã��Ѿ�������Ԫ���޸�Ϊ�㣬�����Ԫ�ز��ùܡ�
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {

        // ����������Ƿ���ֹ��㣬���n[index] == true,���ʾindex��ȫ��Ϊ��
        boolean[] n = new boolean[matrix[0].length];
        // �����������Ƿ���ֹ��㣬���m[index] == true�����ʾindex��ȫ��Ϊ��
        boolean[] m= new boolean[matrix.length];
        int h = 0;
        int w = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    // ��j�У�ȫ��Ϊ��
                    n[j] = true;
                    m[i] = true;

                    // ����֮ǰ��Ԫ�أ��޸�Ϊ��
                    for(; h < i; h++){
                        matrix[h][j]=0;
                    }
                    for(; w < j; w++){
                        matrix[i][w]=0;
                    }
                    h = 0;
                    w = 0;
                }else{
                    // �����ǰԪ�ز�Ϊ�㣬�ж��Ƿ��0Ԫ����ͬһ�����ꡢ�����ꣻ��������޸�Ϊ��
                    if(n[j] || m[i]){
                        matrix[i][j] =0;
                    }
                }

            }
        }
    }

    /**
     * ����һ��������0�Ȳ��ҳ�����������Map����ȥִ��ת0����
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {

        Map<Integer, List<Integer>> container = new HashMap<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){

                    List<Integer> rows = container.get(i);
                    if(rows == null){
                        rows = new ArrayList<>();
                        rows.add(j);
                        container.put(i, rows);

                    }else{
                        rows.add(j);
                    }
                }
            }
        }


        container.forEach((i, j) ->{

            for(int t = 0; t < matrix[0].length; t++){
                matrix[i][t] = 0;
            }

            j.forEach(t ->{
                for(int k =0; k < matrix.length; k++){
                    matrix[k][t]=0;
                }
            });
        });
    }


}
