package com.up.arithmetic;

import java.util.Arrays;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-03-31 20:44
 */
public class L1 {


    public static void main(String[] args) {
        L1 l = new L1();
        System.out.println(l.find1(new int[]{1,2,3,3,3,4,5,6}, 3));
    }

    public int find1(int[] nums, int target){
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length-1;
        int index = 0;
        while(left <= right){

            int mid = (left + right)/2;
            if(nums[mid] > target){
                right = mid-1;
            }else if(nums[mid] < target){
                left = mid+1;
            }else {

                index = mid;
                break;
            }
        }

        int prev = target;
        for(int i = index+1; i < nums.length; i++){
            if(prev != nums[i]){
                return i-1;
            }
        }


        return -1;

    }
}
