package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode491 {


    static List<Integer> temp = new ArrayList<>();
    static List<List<Integer>> result = new ArrayList<List<Integer>>();


    /**
     * 排列组合的时候，每一个元素都只有两种选择，加入和不加入；设数组长度为n;那么排列组合总的个数为 2^n
     * @param nums
     * @return
     */


    public static void main(String[] args) {
        int[] nums = new int[]{7,7};
        leetcode491 l = new leetcode491();
        l.dfs(0, Integer.MIN_VALUE, nums);

        for(int i = 0; i < result.size(); i++){
            System.out.println("[");
            for(int j = 0; j < result.get(i).size(); j++){
                System.out.print(result.get(i).get(j) + ",");
            }

        }

    }




    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
//            if (temp.size() >= 2) {
            result.add(new ArrayList<Integer>(temp));
//            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }

        // cur值比last小
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }


    /**
     * 在calc基础上，我们需要做以下几点处理
     * 1、nums不是完全递增的，需要判断比之前的值要小的情况；
     * 2、子序列长度大于等于2；
     * 3、nums中可能存在重复的值，并且重复的值理解为升序（需要注意的是 [7,7,7]因为重复只会有三个子序列）；
     * @param nums
     * @param cur
     * @param last 当前temp最后一个元素的值
     */
    public void findSubsequences1(Integer[] nums, int cur, int last){

        if(cur == nums.length){
            // 子序列长度 >= 2
            if(temp.size() >=2){
                result.add(new ArrayList<>(temp));
            }
            return;
        }



        // 当前值加入排列
        temp.add(nums[cur]);
        calc(nums, cur+1);

        // 当前值不加入
        temp.remove(nums[cur]);
        calc(nums, cur+1);
    }



    /**
     * 递归获取序列中的子序列
     * @param nums  升序排序的数组，并且没有重复元素
     * @param cur 当前值的索引，每个值只有两种状态，加入temp、不加入temp
     */
    public void calc(Integer[] nums, int cur){
        if(cur == nums.length){
            result.add(new ArrayList<>(temp));
        }

        // 当前值加入排列
        temp.add(nums[cur]);
        calc(nums, cur+1);

        // 当前值不加入
        temp.remove(nums[cur]);
        calc(nums, cur+1);

    }
}
