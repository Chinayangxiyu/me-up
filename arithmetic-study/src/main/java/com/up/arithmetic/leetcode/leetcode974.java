package com.up.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yxy
 * @date 2020/5/27 10:36
 * @description
 */
public class leetcode974 {

    public static void main(String[] args) {
        int[] A = new int[]{0,0,0};

        System.out.println( subarraysDivByK(A, 5));
    }

    //
    public static int subarraysDivByK(int[] A, int K) {

        int sum = 0;
        int count = 0;
        Integer tmp;
//        Map<Integer, Integer> pointor = new HashMap<>(); // key表示满足得索引，value是计数
        int[] indexArray = new int[A.length + 1];
        int index = 0;
//        while(index <= A.length){
//            pointor.put(index++, 0);
//        }

        for(int i = 0; i < A.length; i++){
            for(int j = i; j < A.length; j++){
                sum += A[j];
                if(sum % K == 0){
                    indexArray[j] = indexArray[j];
//                    tmp = pointor.get(i);
                    tmp = indexArray[i];
                    indexArray[j + 1] = indexArray[j+1] + indexArray[i] + 1;
//                    pointor.put(j+1, pointor.get(j+1) + tmp + 1);// 加1
                    count += indexArray[i] + 1;
                    break;
                }
            }
            sum = 0;
        }

        return count;
    }
}
