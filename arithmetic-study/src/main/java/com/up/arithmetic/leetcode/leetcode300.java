package com.up.arithmetic.leetcode;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2020-12-31 16:23
 */
public class leetcode300 {

    public static void main(String[] args) {

        int[] array = new int[]{10,9,2,5,3,7,101,18};
        leetcode300 l = new leetcode300();

        System.out.println(l.lengthOfLIS(array));
    }


    /**
     * f(n) = max(f(n-1)) + 1 && nums[n] > nums[n-1]
     *
     * 1��״̬ת�Ʒ��̣� ������� f(n) = max(f(n-1)) + (nums[n] > nums[n-1] ? 1 : 0)��
     * 2������һ��һγ���飬��ÿ��λ�õ���� ���������С�
     */
    public int lengthOfLIS(int[] nums) {


       int[] dp = new int[nums.length];
        dp[0] = 1;
        int lasfSize = 1;
        //  ��������
       for(int i = 1; i < nums.length; i++){

           // ������ȡ����ÿһ��λ�õ�����������У��ȴ�0��i֮����������
           dp[i] = 1;
           // dp[i]������ʱ���������
           for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
           }

           lasfSize = Math.max(lasfSize, dp[i]);
       }

       return dp[nums.length-1];
    }
}
