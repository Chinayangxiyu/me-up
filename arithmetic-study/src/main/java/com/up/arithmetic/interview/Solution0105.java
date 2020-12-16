package com.up.arithmetic.interview;

import java.util.Objects;

/**
 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 */
public class Solution0105 {

    public static void main(String[] args) {
        Solution0105 s = new Solution0105();

        System.out.println(s.oneEditAway("a",
                "a"));


    }

    /**
     * 双指针分析
     * 分别从左右两端遍历，直到找到不同的字符的位置；
     * 如果两个字符串能互转，那么不同的字符长度不应该超过1，否则不能通过只操作一个步骤进行转换。
     * 【注意问题】
     * （1）边界问题的处理，避免数组越界；在声明j、k的时候初始为字符串长度，个人习惯声明为length()-1；这样声明会导致为空字符串的时候数组下标越界；
     * （2）需要保证j、k的值比i大，需要注意j、k遍历时需要使用（j-1）和（k-1）获取对应的元素。
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
     * 解法一、
     * 1、优先判断字符串的长度差，因为删除、插入、替换一次只能操作一个字符，所以长度差超过2的肯定不能转换。
     * 2、长度差为1的情况，只能操作删除或插入进行处理；删除和插入本质上上一样的，first执行删除等于second执行插入。
     * 3、长度一样的字符串，只能执行替换操作。
     * 4、结果只是判断true、false 所以不需要操作字符串，只需要判断。
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
     * 删除不一样的字符
     */
    public boolean delete(String s1, String s2){

            if("".equals(s1) || "".equals(s2)){
                return true;
            }

            boolean flag = true;
            // 遍历两个字符串
            for (int i = 0, j=0; i < s1.length() && j < s2.length(); ){
                    // 当前位置的两个字符相等，继续判断
                    if(s1.charAt(i) == s2.charAt(j)){
                        i++;
                        j++;

                        // 当前两个字符不相等，则需要删除其中一个字符，具体是删除s1还是s2
                        // 需要判断哪个字符比较长，shan
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
     * 替换不一样的字符处理
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
