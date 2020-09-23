package com.up.arithmetic.leetcode;

import com.up.arithmetic.leetcode.base.QuickSortUtil;

import java.util.*;

public class leetcode47 {



    public static void main(String[] args) {

//        int[] nums = new int[1000];
//
//        for(int i = 0; i < 1000; i++){
//            int v = (int)(Math.random() * 10000);
//        }
//
        leetcode47 l = new leetcode47();
//
//        long start1 = System.currentTimeMillis();
//        l.getMap(nums);
//        System.out.println(System.currentTimeMillis() - start1);
//
//
//        long start2 = System.currentTimeMillis();
//        l.getMapBySort(nums);
//        System.out.println(System.currentTimeMillis() - start2);


        int[] nums = new int[]{1,1,2};
        List<List<Integer>>  list = l.permuteUnique(nums);
        list.forEach(ls -> {
            ls.forEach(i ->{
                System.out.print(i);
            });
            System.out.println();
        });

    }

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

        temp = new Integer[nums.length];
        flag = new boolean[nums.length];


        // 去重：对于重复的元素，我们需要标记当前组合方式已经出现过，后面如果遇到同样的组合方式时，直接跳过；
        // 怎么标记：最终产生的组合数组是temp，通过和temp同样的结构来标记重复元素出现过的组合形式，主要是标记索引位置。
        // 排序后的方式
//        indexMap = getMapBySort(nums);
        seq(0, nums);
        return result;
    }


    // 使用快速排序后的数组获取map
    private void getMapBySort(int[] nums){
        QuickSortUtil.quicksort(nums);
        indexMap = new HashMap<>();
        itemMap = new HashMap<>();
        int pre = nums[0];
        int count = 1;
        for(int i = 1; i< nums.length; i++){

            if(nums[i] == pre){
                count++;
            }else{
                if(count > 0){
                    List<boolean[]> list = new ArrayList<>();
                    indexMap.put(i-1,list);
                    itemMap.put(pre, list);
                }
                pre = nums[i];
                count = 0;

            }
        }

    }


    List<List<Integer>> result = new ArrayList<>();

    Map<Integer, List<boolean[]>> indexMap;
    Map<Integer, List<boolean[]>> itemMap;

    Integer temp[];
    boolean flag[];
    private void seq(int curIndex, int[] nums){
        if(curIndex == nums.length){
            Integer[] copy = Arrays.copyOfRange(temp, 0, nums.length);
            result.add(Arrays.asList(copy));
        }

        for(int i = 0; i < nums.length; i ++){
            if(flag[i]){
              continue;
            }

            if(itemMap.get(nums[curIndex]) != null){

            }


            if(indexMap.get(curIndex) != null){
                List<boolean[]> indexList = indexMap.get(curIndex);

            }

            flag[i] = true;
            temp[i] = nums[curIndex];
            seq(curIndex + 1, nums);
            // 重置
            flag[i] = false;
        }


    }
}
