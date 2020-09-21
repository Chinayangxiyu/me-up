package com.up.arithmetic.leetcode;

import java.util.List;

public class leetcode47 {

    /**
     * 递归回溯，去重
     * 1、递归，回溯去获取所有的排列方式；
     * 2、我们需要去重，将原始数组排序，并将重复的元素取出来；
     * 3、在遍历的过程中记录重复元素所在位置的排列方式，比如重复元素8有两个；
     * 在索引1，2的位置出现过一次了，那么如果当前也出现了同样的情况，则当次递归终止。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        return null;
    }


    int temp[];
    int sortNums[];
    int size;
    int data[];
    boolean falg[];
    private void seq(int ver, int curIndex){

        for(int i = 0; i < size; i++){

            temp[ver] = data[curIndex];


        }
    }
}
