package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yxy
 * @date 2020/5/18 10:27
 * @description
 */
public class leetcode118 {


    public static void main(String[] args){

        List<List<Integer>> result = generate1(5);
        System.out.println(result.toString());
    }

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0){
            return result;
        }
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);

        Integer left = 0;
        Integer right = 0;
        for(int i = 1; i < numRows; i++){
            List<Integer> current = new ArrayList<>();
            current.add(1);
            for(int j =1; j < i; j++){
                left = result.get(i-1).get(j-1);
                right = result.get(i-1).get(j);
                current.add(j, left + right);
            }
            current.add(1);

            result.add(current);
        }



        return result;
    }

    public static List<List<Integer>> generate1(int numRows) {

        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0){
            return result;
        }
        Integer[][] resulrArray = new Integer[numRows][];
        Integer[] first = new Integer[]{1};
        resulrArray[0] = first;

        int left;
        int right;
        for(int i = 1; i < numRows; i++){
            Integer[] current = new Integer[i+1];
            current[0] = 1;
            for(int j =1; j < i; j++){
                left = resulrArray[i-1][j-1];
                right = resulrArray[i-1][j];
                current[j] = left+right;
            }
            current[i] = 1;

            resulrArray[i] = current;
        }

        for (int i = 0; i < resulrArray.length; i++){
            result.add(Arrays.asList(resulrArray[i]));
        }


        return result;
    }
}
