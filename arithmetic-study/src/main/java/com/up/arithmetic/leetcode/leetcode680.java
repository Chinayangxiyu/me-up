package com.up.arithmetic.leetcode;

/**
 * @author yxy
 * @date 2020/5/19 13:41
 * @description
 */
public class leetcode680 {

    public static void main(String[] args) {

//        System.out.println(validPalindrome1("abca", 1));
        String s= "";
        System.out.println(validPalindrome1(s,0, s.length()-1, 1));
    }

    /*
     * @description: 递归比较
     * @date 2020/5/19 16:53
     * @param s
     * @param start 左端起始索引
     * @param end 右端起始索引
     * @param count
     */
    public static boolean validPalindrome1(String s, int leftIndex, int rightIndex, int count) {

        for(; leftIndex<rightIndex; leftIndex++,rightIndex--){
            if(s.charAt(leftIndex) == s.charAt(rightIndex)){
                continue;
            }

            count--;
            if(count < 0){
                return false;
            }

            return validPalindrome1(s, leftIndex+1, rightIndex, count) || validPalindrome1(s, leftIndex, rightIndex-1, count);
        }


        return true;
    }
}
