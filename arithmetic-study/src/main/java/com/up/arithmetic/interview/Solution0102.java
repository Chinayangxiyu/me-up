package com.up.arithmetic.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ���������ַ��� s1 �� s2�����дһ������ȷ������һ���ַ������ַ��������к��ܷ�����һ���ַ�����
 */
public class Solution0102 {

    public boolean CheckPermutation(String s1, String s2) {

        if(Objects.equals(s1,s2)){
            return true;
        }

        if(s1 == null || s1 == null || s1.length() != s2.length()){
            return false;
        }


        Map<Character, Integer> temp = new HashMap<>();
        for(int i = 0; i< s1.length(); i++){

            Integer s1count = temp.get(s1.charAt(i));
            if(s1count == null){
                temp.put(s1.charAt(i), 1);
            }else{
                temp.put(s1.charAt(i), s1count + 1);
            }

            Integer s2count = temp.get(s2.charAt(i));
            if(s2count == null){
                temp.put(s2.charAt(i), -1);
            }else{
                temp.put(s2.charAt(i), s2count-1);
            }
        }


        for(Integer count : temp.values()){
            if(count != 0){
                return false;
            }
        }


        return true;

    }


    /**
     * �ⷨ����ʹ��int����ȥ������㣻���Ǹ÷�����ȱ�����޶����ַ���ΪСд��ĸ��
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation1(String s1, String s2) {

        if(Objects.equals(s1,s2)){
            return true;
        }

        if(s1 == null || s1 == null || s1.length() != s2.length()){
            return false;
        }


        int[] temp = new int[26];
        for(int i = 0; i< s1.length(); i++) {
            int f1 = s1.charAt(i) - 97;

            temp[f1] = temp[f1] + 1;

            int f2 = s2.charAt(i) -97;
            temp[f2] = temp[f2] - 1;
        }

        for(int i = 0; i < 26; i++){
            if(temp[i] != 0){
                return false;
            }
        }
            return true;
    }
}
