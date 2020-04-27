package com.up.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @author yxy
 * @date 2020/4/27 15:57
 * @description
 */
public class leetcode42 {

    public static void main(String[] args) {

        int[] array = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trapNew(array, 0, array.length-1);
        System.out.println(result);
    }

    // 分析思路，要获取容量就需要获取最大值（first），和第二大值（second）中间的容量；
    // 中间的容量等于 secod一次减去区域内所有的值的“正”结果，
    // 然后以当前first,second为分解，将剩下的未参与计算的元素分离成两个子数组递归计算
    public static int trap(int[] array) {
        return trapNew(array, 0, array.length-1);
    }

    public static int trapNew(int[] array, int start, int end) {

       if(end <= start){
           return 0;
       }



       int maxHigth = 0;
       int maxHigthIndex = 0;
       int secondHigth = 0;
       int secondHigthIndex = 0;

       int currentStart = start;

       for(; currentStart <= end; currentStart ++){
           if( array[currentStart] >= maxHigth){
               secondHigth = maxHigth;
               secondHigthIndex = maxHigthIndex;
               maxHigth = array[currentStart];
               maxHigthIndex = currentStart;
           }else if(array[currentStart] >= secondHigth){
               secondHigth = array[currentStart];
               secondHigthIndex = currentStart;
           }
       }

        if(secondHigth == 0 || maxHigth == 0){
            return 0;
        }


        int minIndex =  maxHigthIndex < secondHigthIndex ? maxHigthIndex : secondHigthIndex;
        int maxIndex =  maxHigthIndex > secondHigthIndex ? maxHigthIndex : secondHigthIndex;
        int currentCapacity = calcCapacity(array, minIndex, maxIndex);
        int left = trapNew( array, start, minIndex);
        int right = trapNew( array, maxIndex , end );



        return left + right + currentCapacity;
    }

    public static int calcCapacity(int[] array, int start, int end){
        int min = array[start] < array[end] ? array[start] : array[end];
        int capacity = 0;
        for(; start <= end; start ++){
            if(min - array[start] > 0){
                capacity += min - array[start];
            }
        }

        return capacity;
    }
}
