package com.up.arithmetic.leetcode;

import java.util.*;

public class leetcode350 {

    public static void main(String[] args){
        int[] nums1 = new int[]{1,2,2,3,3};
        int[] nums2 = new int[]{2,2,3};
        int[] result = intersect(nums1, nums2);
        Arrays.stream(result).forEach(o -> {
            System.out.println(o);
        });
    }


    public static int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> container = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums1.length; i++) {
            if (container.containsKey(nums1[i])) {
                container.put(nums1[i], container.get(nums1[i]) + 1);
            }else{
                container.put(nums1[i], 1);
            }
        }


        List<Integer> list = new ArrayList<>();
        int[] result = new int[nums1.length > nums1.length ? nums1.length : nums2.length];
        int point = 0;
        HashMap<Integer, Integer> container1 = new HashMap<Integer, Integer>();
        for (int j = 0; j < nums2.length; j++) {
            if (container.containsKey(nums2[j]) && (container1.get(nums2[j]) == null || container1.get(nums2[j]) < container.get(nums2[j]))) {

                if(container1.get(nums2[j]) == null){
                    container1.put(nums2[j], 1);
                    result[point++] = nums2[j];
                }else{
                    container1.put(nums2[j], container1.get(nums2[j]) + 1);
                    result[point++] = nums2[j];
                }
            }
        }




        return Arrays.copyOfRange(result, 0, point);
    }
}
