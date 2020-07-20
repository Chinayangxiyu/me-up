package com.up.arithmetic.leetcode;

public class leetcode167 {



    public static void main(String[] args) {

//        int[] nums = new int[]{2,3,4,6,8,9,13};
//        int[] nums = new int[]{-1,0};
        int[] nums = new int[]{0,0,3,4};
        int[] result = twoSum(nums, 0);
        System.out.println(result[0]+","+ result[1]);
    }

    /**
     * ˫ָ��
     * ��ָ��li ��ָ��ri,��ʼ��li=0;ri=numbers.length-1
     * ��nums[li] + nums[ri] > targetʱ��
     * ��Ϊ֮��ı���˳��Ϊnums[li++] >= nums[li]
     * ���� ���� > ri��ֵ�����ò������
     * ��ri�� ��֤nums[li] + nums[ri] < targetλ��
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

        int tmp;
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
