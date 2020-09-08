package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode77 {

    public static void main(String[] args) {
        leetcode77 l = new leetcode77();
        l.combine(5, 2);

    }

    List<List<Integer>> result = new ArrayList<>();
    Integer[] temp;
    int max;
    int size;
    public List<List<Integer>> combine(int n, int k) {
        max = n;
        size = k;
        temp = new Integer[k];
        build(1, 0);
        return result;
    }


    /**
     * cur 表示其实子
     * index 表示索引
     * @param cur
     * @param index
     */
    private void build(int cur, int index){

        if(index == size-1){
            for(;cur <= max; cur++){
                temp[index] = cur;
                result.add(Arrays.asList(temp.clone()));

            }
        }else{
            for(;cur <= max; cur++){
                temp[index] = cur;
                build(cur+1, index+1);
            }
        }

    }




}
