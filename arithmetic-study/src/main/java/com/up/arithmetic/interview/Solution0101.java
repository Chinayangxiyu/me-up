package com.up.arithmetic.interview;

/**
 ʵ��һ���㷨��ȷ��һ���ַ��� s �������ַ��Ƿ�ȫ����ͬ��
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
