package com.up.arithmetic.leetcode;

/**
 * @author yxy
 * @date 2020/6/2 17:00
 * @description
 */
public class GetOffer64 {

    public static void main(String[] args) {

        System.out.println(sumNums(2));
    }

    // Î»ÔËËã
    public static int sumNums(int n) {
        int i = n;
        boolean b1 = ((i/2) == 0 && (i = 0) >-1);
        boolean b2 = ((i%2) == 1) && ((i= n/2 +1)  > 0);

        return (n + 1) * n/2 + i;
    }
}
