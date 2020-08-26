package com.up.arithmetic.leetcode;

import java.util.*;

public class leetcode17 {


    public static final Map<Character, Character[]> M = new HashMap<>();
    static {
        M.put('2', new Character[]{'a', 'b', 'c'});
        M.put('3', new Character[]{'d', 'e', 'f'});
        M.put('4', new Character[]{'g', 'h', 'i'});
        M.put('5', new Character[]{'j', 'k', 'l'});
        M.put('6', new Character[]{'m', 'n', 'o'});
        M.put('7', new Character[]{'p', 'q', 'r', 's'});
        M.put('8', new Character[]{'t', 'u', 'v'});
        M.put('9', new Character[]{'w', 'x', 'y', 'z'});
    }


    public static void main(String[] args) {
        leetcode17 l = new leetcode17();
        List<String> list = l.letterCombinations("23");
        list.forEach(s ->{
            System.out.println(s);
        });
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() ==0){
            return Collections.EMPTY_LIST;
        }

        dfs(digits.toCharArray(), 0, new char[digits.length()]);
        return result;
    }

    Character[] temp;
    List<String> result = new ArrayList<>();

    public void dfs(char[] array, int cur, char[] target){

        if(cur == array.length){
            result.add(new String(target));
            return;
        }

        temp = M.get(array[cur]);
        for(int i = 0; i < temp.length; i++){
            target[cur] = temp[i];
            dfs(array, cur+1, target);
            temp = M.get(array[cur]);
        }

    }
}
