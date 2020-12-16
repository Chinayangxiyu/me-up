package com.up.arithmetic.interview;


import java.util.*;

public class Solution0108 {

    public static void main(String[] args) {
        Solution0108 s = new Solution0108();


    }



    /**
     * 方法二、
     * （1）遍历的时候如果找到零，则将当前值的索引位置缓存；
     * （2）继续遍历，如果当前值的坐标，位于缓存中任意位置，则值被修改为零。
     * （3）如果当前位置为零，则需要倒回去将对应位置，已经遍历的元素修改为零，后面的元素不用管。
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {

        // 缓存横坐标是否出现过零，如果n[index] == true,则表示index列全部为零
        boolean[] n = new boolean[matrix[0].length];
        // 缓存纵坐标是否出现过零，如果m[index] == true，则表示index行全部为零
        boolean[] m= new boolean[matrix.length];
        int h = 0;
        int w = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    // 第j列，全部为零
                    n[j] = true;
                    m[i] = true;

                    // 遍历之前的元素，修改为零
                    for(; h < i; h++){
                        matrix[h][j]=0;
                    }
                    for(; w < j; w++){
                        matrix[i][w]=0;
                    }
                    h = 0;
                    w = 0;
                }else{
                    // 如果当前元素不为零，判断是否和0元素在同一横坐标、纵坐标；如果是则修改为零
                    if(n[j] || m[i]){
                        matrix[i][j] =0;
                    }
                }

            }
        }
    }

    /**
     * 方法一、将所有0先查找出来，缓存在Map，再去执行转0操作
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
