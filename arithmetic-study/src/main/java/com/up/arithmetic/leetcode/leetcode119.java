package com.up.arithmetic.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yxy
 * @date 2020/5/20 15:35
 * @description
 */
public class leetcode119 {

    public static void main(String[] args){
        System.out.println(getRow(3).toString());
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);
        }
        return cur;


    }
}
