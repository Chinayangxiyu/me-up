package com.up.arithmetic.leetcode;

/**
 * @author yxy
 * @date 2020/5/8 15:14
 * @description
 */
public class leetcode221 {

    public static void main(String[] args) {
        char[][] array = new char[][]{{'1','1','1'},{'1','1','1'},{'1','1','1'}};
        System.out.println(maximalSquare(array));
    }

    // 1、暴力遍历，每一行记录连续1的长度 和起止索引，这段值可能组成正方形
    // 2、当前行和已经存在的
    public static int maximalSquare(char[][] matrix) {

        int endIndex=0;
        int size=0;
        int maxSize = 0;

        for(int i = 0; i < matrix.length; i++){
            char[] childArray = matrix[i];
            for(int j = 0; j < childArray.length; j++){
                if(childArray[j] == '1'){
                    if(maxSize == 0){
                        maxSize = 1;
                    }
                    endIndex = j;
                    size++;
                }
                if(childArray[j] == '0' || j ==  childArray.length-1){
                    // 计算当前可能存在的面积
                    if(size != 0 && size > maxSize && matrix.length >= i  +size){
                        int tmpIndex = endIndex;
                        int higth = i + 1;
                        while ((higth-i) <= size && higth < matrix.length  && tmpIndex > endIndex-size  && matrix[higth][tmpIndex] == '1'){
                            if(tmpIndex-- == endIndex-size + 1){
                                higth++;
                            }

                        }
                        if((higth-i + 1)  <= size){
                            maxSize = higth-i + 1;
                        }
                    }
                    size = 0;
                }


            }
        }

        return maxSize * maxSize;
    }
}
