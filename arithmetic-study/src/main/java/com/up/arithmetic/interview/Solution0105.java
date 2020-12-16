package com.up.arithmetic.interview;

import java.util.Objects;

/**
 ʵ��һ���㷨��ȷ��һ���ַ��� s �������ַ��Ƿ�ȫ����ͬ��
 */
public class Solution0105 {

    public static void main(String[] args) {
        Solution0105 s = new Solution0105();

        System.out.println(s.oneEditAway("a",
                "a"));


    }

    /**
     * ˫ָ�����
     * �ֱ���������˱�����ֱ���ҵ���ͬ���ַ���λ�ã�
     * ��������ַ����ܻ�ת����ô��ͬ���ַ����Ȳ�Ӧ�ó���1��������ͨ��ֻ����һ���������ת����
     * ��ע�����⡿
     * ��1���߽�����Ĵ�����������Խ�磻������j��k��ʱ���ʼΪ�ַ������ȣ�����ϰ������Ϊlength()-1�����������ᵼ��Ϊ���ַ�����ʱ�������±�Խ�磻
     * ��2����Ҫ��֤j��k��ֵ��i����Ҫע��j��k����ʱ��Ҫʹ�ã�j-1���ͣ�k-1����ȡ��Ӧ��Ԫ�ء�
     *
     * @return
     */
    public boolean oneEditAway(String first, String second){


        if(Math.abs(first.length() - second.length()) > 1){
            return false;
        }


        int i = 0;
        for(; i < first.length() && i < second.length(); i++){
            if(first.charAt(i) != second.charAt(i)){
                break;
            }
        }


        int j = first.length();
        int k = second.length();
        for(; j > i && k > i; j--, k--){
            if (first.charAt(j-1) != second.charAt(k-1)){
                break;
            }
        }

        return Math.abs(j - i) <= 1 && Math.abs (k - i) <= 1;
    }






    /**
     * �ⷨһ��
     * 1�������ж��ַ����ĳ��Ȳ��Ϊɾ�������롢�滻һ��ֻ�ܲ���һ���ַ������Գ��Ȳ��2�Ŀ϶�����ת����
     * 2�����Ȳ�Ϊ1�������ֻ�ܲ���ɾ���������д���ɾ���Ͳ��뱾������һ���ģ�firstִ��ɾ������secondִ�в��롣
     * 3������һ�����ַ�����ֻ��ִ���滻������
     * 4�����ֻ���ж�true��false ���Բ���Ҫ�����ַ�����ֻ��Ҫ�жϡ�
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway1(String first, String second) {


        if(Math.abs(first.length() - second.length()) > 1){
            return false;
        }

        if(first.length() == second.length()){
            return replace(first, second);
        }else{
            return delete(first, second);
        }

    }

    /**
     * ɾ����һ�����ַ�
     */
    public boolean delete(String s1, String s2){

            if("".equals(s1) || "".equals(s2)){
                return true;
            }

            boolean flag = true;
            // ���������ַ���
            for (int i = 0, j=0; i < s1.length() && j < s2.length(); ){
                    // ��ǰλ�õ������ַ���ȣ������ж�
                    if(s1.charAt(i) == s2.charAt(j)){
                        i++;
                        j++;

                        // ��ǰ�����ַ�����ȣ�����Ҫɾ������һ���ַ���������ɾ��s1����s2
                        // ��Ҫ�ж��ĸ��ַ��Ƚϳ���shan
                    }else{
                        if (!flag){
                            return false;
                        }
                        flag = false;
                        if (s1.length() < s2.length()){
                            j++;
                        }else{
                            i++;
                        }
                    }

            }

            return true;

    }


    /**
     * �滻��һ�����ַ�����
     */
    public boolean replace(String s1, String s2){

        int count = 0;
        for(int i =0 ; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                if(count == 0){
                    count ++;
                }else {
                    return false;
                }
            }
        }

        return true;
    }
}
