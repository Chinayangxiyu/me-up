package com.up.arithmetic.interview;

import java.util.Objects;

/**
 �ַ���ѹ���������ַ��ظ����ֵĴ�������дһ�ַ�����ʵ�ֻ������ַ���ѹ�����ܡ����磬
 �ַ���aabcccccaaa���Ϊa2b1c5a3������ѹ��������ַ���û�б�̣��򷵻�ԭ�ȵ��ַ�����
 ����Լ����ַ�����ֻ������СдӢ����ĸ��a��z����
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
