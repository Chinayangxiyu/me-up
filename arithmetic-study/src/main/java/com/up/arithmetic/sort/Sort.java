package com.up.arithmetic.sort;


import java.util.Arrays;

/**
 * 归并排序示例
 */
public class Sort {


    public static void main(String[] args) {

        int[] nums1 = new int[]{4,3,2,8,9};
        int[] nums2 = new int[]{1,7,4,3,0};


        int[] result = mergeSort(nums1, nums2);
        Arrays.stream(result).forEach(o -> {
            System.out.println(o);
        });
    }

    /**
     * 归并排序
     * 时间复杂度log（n）
     * @param
     * @return
     */
    public static int[] mergeSort(int[] nums1, int nums2[]){

        if(nums1.length <=1 && nums2.length <= 1){
            return merge(nums1, nums2);
        }

        int mid1 = nums1.length/2 -1;
        int[] nums1L = Arrays.copyOfRange(nums1, 0, mid1+1);
        int[] nums1R = Arrays.copyOfRange(nums1, mid1+1, nums1.length);
        nums1 = mergeSort(nums1L, nums1R);


        int mid2 = nums2.length/2 -1;
        int[] nums2L = Arrays.copyOfRange(nums2, 0, mid2+1);
        int[] nums2R = Arrays.copyOfRange(nums2, mid2+1, nums2.length);
        nums2 = mergeSort(nums2L, nums2R);

        return merge(nums1, nums2);
    }


    public static   int[] merge(int[] nums1, int[] nums2){

        int[] result = new int[nums1.length + nums2.length];

        int i =0, j = 0;
        for(; i < nums1.length && j < nums2.length;){
            if(nums1[i] < nums2[j]){
                result[i + j] = nums1[i++];
            }else{
                result[i + j] = nums2[j++];

            }
        }

        if( i == nums1.length){
            while(j < nums2.length){
                result[i + j] = nums2[j++];
            }
        }else{
            while(i < nums1.length){
                result[i + j] = nums1[i++];
            }
        }

        return result;
    }

}
