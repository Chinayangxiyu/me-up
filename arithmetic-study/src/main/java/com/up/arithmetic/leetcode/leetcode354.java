package com.up.arithmetic.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-04-06 15:26
 */
public class leetcode354 {

    public int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (o1, o2) -> o1[0] - o2[0]);

        return  1;
    }


}
