package com.up.arithmetic.leetcode.base;

/**
 * 快速排序工具方法，
 * 1、取一个初始值cur（最左端或右端）；
 * 2、从左边开始找，直到找到比cur大的值，和cur互换（此时cur左边的值都比它小）；
 * 3、然后从右边开始找到一个比cur小的值，和cur互换（此时cur右边的值都比它大）；
 * 4、重复2，3步骤，直到left和right值重叠；
 * 5、递归遍历，最终完成所有元素的排序
 */
public class QuickSortUtil {

    // [[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]]
    public static void main(String[] args) {
        int[] array = new int[]{12,7,14,9,10,11};
        quicksort(array);

        for(int i = 0; i< array.length; i++){
            System.out.println(array[i]);
        }

    }


    /**
     *
     */
    public static void quicksort(int[] nums){

        quicksort(0, nums.length-1, nums);
    }


    private static void quicksort(int left, int right, int[] nums){

        if(left >= right){
            return ;
        }

        int i = left;
        int j = right;

        cur = nums[right];
        while(left < right){
            left = leftSort(left, right, nums);
            right = rightSort(left, right, nums);
        }



        quicksort(i, left-1, nums);
        quicksort(right+1, j, nums);

    }


    private static int cur;
    private static int leftSort(int left, int right, int[] nums){
        for(int i  = left; i < right; i++){
            if (nums[i] > cur){
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;
                return i;
            }
        }

        return right;
    }

    private static int rightSort(int left, int right, int[] nums){
        for(int i  = right; i > left; i--){
            if (nums[i] < cur){
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                return i;
            }
        }

        return left;
    }
}
