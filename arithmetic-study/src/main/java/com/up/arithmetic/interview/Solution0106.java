package com.up.arithmetic.interview;

import java.util.Objects;

/**
 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，
 字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
 你可以假设字符串中只包含大小写英文字母（a至z）。
 */
public class Solution0106 {

    public static void main(String[] args) {
        Solution0106 s = new Solution0106();

        System.out.println(s.compressString("SSSmrrriiiNNIAOBBBFffWWaaaEEEkiyyvOfRHHDDDXXXEEEzxXeQQQtwwfffkkm"));


    }

    public String compressString(String S) {

        if(Objects.isNull(S) || S.length() <= 2){
            return S;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        char cur = S.charAt(0);
        for(int i =1; i < S.length(); i++) {
            if (S.charAt(i) == cur) {
                count++;
            } else {
                sb.append(cur);
                sb.append(count);

                if(sb.length() >= S.length()){
                    return S;
                }

                count = 1;
                cur = S.charAt(i);

            }
        }

        sb.append(cur);
        sb.append(count);
        if(sb.length() >= S.length()){
            return S;
        }



        return sb.toString();

    }

}
