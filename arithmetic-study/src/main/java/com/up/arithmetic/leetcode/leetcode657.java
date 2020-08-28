package com.up.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leetcode657 {

    public static void main(String[] args) {

        System.out.println((int)'U');
        System.out.println((int)'D');
        System.out.println((int)'R');
        System.out.println((int)'L');
    }

    // 0:U, 1:D, 2:R, 3:L
    int[] count = new int[4];
    public boolean judgeCircle(String moves) {

        if(moves.length() %2 != 0){
            return false;
        }

        for(int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'U') {
                count[0] = count[0] + 1;
                continue;
            }
            if (moves.charAt(i) == 'D') {
                count[1] = count[1] + 1;
                continue;

            }

            if (moves.charAt(i) == 'R') {
                count[2] = count[2] + 1;
                continue;
            }
            if (moves.charAt(i) == 'L') {
                count[3] = count[3] + 1;
                continue;
            }

        }
        return count[0] == count[1] && count[2] == count[3];
    }

    public boolean judgeCircle2(String moves) {

        if(moves.length() %2 != 0){
            return false;
        }

        int max = moves.length()/2;
        for(int i = 0; i < moves.length(); i++) {

            if (moves.charAt(i) == 'U') {
                count[0] = count[0] + 1;
                if(count[0] > max){
                    return false;
                }
                continue;
            }
            if (moves.charAt(i) == 'D') {
                count[1] = count[1] + 1;
                if(count[1] > max){
                    return false;
                }
                continue;

            }

            if (moves.charAt(i) == 'R') {
                count[2] = count[2] + 1;
                if(count[2] > max){
                    return false;
                }
                continue;
            }
            if (moves.charAt(i) == 'L') {
                count[3] = count[3] + 1;
                if(count[3] > max){
                    return false;
                }
                continue;
            }

        }
        return count[0] == count[1] && count[2] == count[3];
    }
}
