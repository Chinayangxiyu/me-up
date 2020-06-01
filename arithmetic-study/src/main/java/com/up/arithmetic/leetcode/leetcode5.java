package com.up.arithmetic.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yxy
 * @date 2020/5/20 13:56
 * @description
 */
public class leetcode5 {


    public static void main(String[] args) {
//        System.out.println(0 ^ 0);
//        System.out.println(1 ^ 1);
//        System.out.println(1 ^ 0);
        System.out.println(longestPalindrome("adada")); // "adada"
    }
    public static int findTheLongestSubstring(String s) {

        return 1;
    }

    // 1、奇数长度中间值左右相等，偶数长度
    // 2、使用栈
    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return s;
        }

        char[] maxSub = new char[s.length()];
        char[] tmpSub = new char[s.length()];

        maxSub[0] = s.charAt(0);
        int count = 1;
        int left=0;
        int right = 0;

        int pR;
        int pL;

        for(int i = 1; i < s.length(); i++){
            pR = i;
            pL = i-1;
            while(pL >=0 && pR < s.length() && s.charAt(pR)  == s.charAt(pL)){
                tmpSub[pL] = s.charAt(pL);
                tmpSub[pR] = s.charAt(pR);
                pR++;
                pL--;
            }

            // 如果出现回文串比目前 最长回文串长，则覆盖
            if(count < (pR - pL -1)){
                left = pL+1;
                right = pR -1;
                count = right - left +1;

                char[] a = maxSub;
                maxSub = tmpSub;
                tmpSub = a;

            }

            pR = i;
            pL = i-2;
            while(pL >=0 && pR < s.length() && s.charAt(pR)  == s.charAt(pL)){
                tmpSub[pL] = s.charAt(pL);
                tmpSub[pR] = s.charAt(pR);
                pR++;
                pL--;
            }

            if(count < (pR - pL -1)){
                left = pL+1;
                right = pR -1;
                count = right - left +1;
                tmpSub[i-1] = s.charAt(i-1);

                char[] a = maxSub;
                maxSub = tmpSub;
                tmpSub = a;

            }

            if(count == s.length() || ( i >= s.length()/2 && count > 2 * (s.length() -i-1 ))){
                break;
            }
        }

        return new String(Arrays.copyOfRange(maxSub, left, right+1));
    }
}