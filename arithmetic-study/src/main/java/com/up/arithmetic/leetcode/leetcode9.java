package com.up.arithmetic.leetcode;

public class leetcode9 {

    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        if(x == 0){
            return true;
        }

        int n = 0;
        for(int i = x; x > 0; i /= 10){
            int j = i % 10;
            n = n*10 + j;
        }

        if(n == x){
            return true;
        }

        return false;
    }
}
