package com.up.arithmetic.interview;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 */
public class Solution0109 {

    public static void main(String[] args) {
        Solution0109 s = new Solution0109();


    }


    /**
     * 1、先遍历找出每个字符不同的索引位置，并将字符缓存在temp数组
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {

        if(s1.length() != s2.length()){
            return false;
        }

        if((s2 + s2).contains(s1)){
            return true;
        }

        return false;
    }

}
