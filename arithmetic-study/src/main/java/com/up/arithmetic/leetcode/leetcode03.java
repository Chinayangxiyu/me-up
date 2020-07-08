package com.up.arithmetic.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class leetcode03 {


    public static void main(String[] args) {

        System.out.println("---------");
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {

        Set<Character> tmpMap = new HashSet<>();
        int start = 0;
        int maxSize = 0;
        int curSzie = 0;
        for(int i = 0; i < s.length(); i++){

            if(!tmpMap.contains(s.charAt(i))){
                tmpMap.add(s.charAt(i));
                curSzie ++;
                continue;
            }

            if(curSzie > maxSize){
                maxSize = curSzie;
            }

            while(s.charAt(start) != s.charAt(i)){
                tmpMap.remove(s.charAt(start++));
            }

            start += 1;
            curSzie = i - start + 1;

            tmpMap.add(s.charAt(i));

        }

        if(curSzie > maxSize){
            return curSzie;
        }

        return maxSize;
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // ��ϣ���ϣ���¼ÿ���ַ��Ƿ���ֹ�
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // ��ָ�룬��ʼֵΪ -1���൱���������ַ�������߽����࣬��û�п�ʼ�ƶ�
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // ��ָ�������ƶ�һ���Ƴ�һ���ַ�
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // ���ϵ��ƶ���ָ��
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // �� i �� rk ���ַ���һ�����������ظ��ַ��Ӵ�
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }

}
