package com.up.arithmetic.interview;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ַ�����ת�����������ַ���s1��s2�����д������s2�Ƿ�Ϊs1��ת���ɣ����磬waterbottle��erbottlewat��ת����ַ�������
 */
public class Solution0109 {

    public static void main(String[] args) {
        Solution0109 s = new Solution0109();


    }


    /**
     * 1���ȱ����ҳ�ÿ���ַ���ͬ������λ�ã������ַ�������temp����
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
