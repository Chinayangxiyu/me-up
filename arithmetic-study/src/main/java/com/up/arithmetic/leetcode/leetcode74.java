package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class leetcode74 {

    public static void main(String[] args) {
        leetcode74 l = new leetcode74();

    }

    // 转译unix绝对路径
    // (1)多个连续'/'由一个/替换
    public String simplifyPath(String path) {

        Stack<Character> temp = new Stack();
        temp.push('/');
        int flag = 2;
        for (int i = 0; i < path.length(); i++){

            if(temp.peek() == '/'){
                if(path.charAt(i) == '.'){
                    temp.push('.');
                }
            }else if(temp.peek() == '.'){
                // .表示当前目录，可以直接去掉
                if(path.charAt(i) == '/'){
                    temp.pop();
                }else if(path.charAt(i) == '.'){
                    while(!temp.empty() && flag >= 0){
                        temp.pop();
                        flag --;
                    }
                }
            }
        }

        char[] array = new char[temp.size()];

//        Character[] array = temp.toArray();
        return "";
    }




}
