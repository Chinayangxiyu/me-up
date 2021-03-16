package com.up.arithmetic.interview;

/**
 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 */
public class Solution0101 {

    public static void main(String[] args) {
        System.out.println(17 * 18);
        System.out.println(21 * 12);

    }

    public boolean isUnique(String astr) {

        for(int i = 0; i < astr.length()-1; i++){
            for(int j = i+1; j < astr.length(); j++){
                if(astr.charAt(i) == astr.charAt(j)){
                    return false;
                }
            }
        }

        return true;
    }
}
