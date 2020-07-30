package com.up.arithmetic.leetcode;

public class leetcode167 {



    public static void main(String[] args) {

//        int[] nums = new int[]{2,3,4,6,8,9,13};
//        int[] nums = new int[]{-1,0};
//        int[] nums = new int[]{0,0,3,4};
        int[] nums = new int[]{-1,0,0,2,4,5,8,14,15,19};
        int[] result = twoSum(nums, 9);
        System.out.println(result[0]+","+ result[1]);
    }

    /**
     * 双指针
     * 令：左指针li 右指针ri,初始化li=0;ri=numbers.length-1
     *
     * 当nums[li] + nums[ri] > target时，
     * 因为之后的遍历顺序为nums[li++] >= nums[li]
     * 所以 索引 > ri的值都不用参与计算
     * 将ri减 保证nums[li] + nums[ri] < target位置
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {

        int mid = target/2;

        if(target < 0){
            mid-=1;
        }

        for(int i = 0, j=numbers.length-1; i<j && numbers[j] >= mid;){
            if(numbers[i] + numbers[j] > target){
                j--;
                continue;
            }

            if(numbers[i] + numbers[j] < target){
                i++;
            }

            if(numbers[i] + numbers[j] == target){
                return new int[]{i+1,j+1};
            }

        }

        return new int[]{};
    }
}
