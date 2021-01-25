package com.up.arithmetic.leetcode.base;

/**
 * 快速排序工具方法，
 * 1、取一个初始值cur（最左端或右端）；
 * 2、从左边开始找，直到找到比cur大的值，和cur互换（此时cur左边的值都比它小）；
 * 3、然后从右边开始找到一个比cur小的值，和cur互换（此时cur右边的值都比它大）；
 * 4、重复2，3步骤，直到left和right值重叠；
 * 5、递归遍历，最终完成所有元素的排序
 */
public class QuickSortUtil2 {

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


    /**
     * 1、固定一个基准值cur，为最右边的值；
     * 2、第一步从左边开始遍历，找到比cur值大的元素 a，返回索引 aIndex；
     * 3、第二步从右边开始遍历，找到比cur小的元素b,返回索引bIndex;
     * 4、一次循环结束，互换aIndex和bIndex索引的值，此时最右边的元素还是基准值；
     * 5、重复2，3，4步骤，直到最后一次从左边开始找，left和right指针撞到了一起；本次循环结束；
     * 以指针撞到一起的位置，nums[left] 和最右边的元素互换，并二分为两个区间继续遍历。
     *
     * 【注意1】：左右指针的遍历有先后顺序；
     * 如果选定的是右边的基准值，那么需要先从左边查找；
     * 反之如果先选定左边的基准值，需要先从右边查找。
     * 【注意2】：最后的指针重叠的操作，肯定是由先查找一方发起的。
     */
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

        nums[j] = nums[left];
        nums[left] = cur;


        quicksort(i, left-1, nums);
        quicksort(right + 1, j, nums);

    }


    private static int cur;
    private static int leftSort(int left, int right, int[] nums){
        for(int i  = left; i < right; i++){
            if (nums[i] > cur){
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
