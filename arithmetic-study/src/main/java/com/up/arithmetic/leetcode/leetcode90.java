package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-03-31 10:34
 */
public class leetcode90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList();
        find(nums, res, 0, 0);

        return res;
    }

    LinkedList<Integer> cur = new LinkedList();
    private void find(int[] nums, List<List<Integer>> res, int p, int prev){

        if(p == nums.length){
            res.add(new ArrayList(cur));
            return;
        }

        // 相等 必须加入
        if(nums[p] == prev){
            cur.add(nums[p]);
            find(nums, res, p+1, nums[p]);
        }else{

            cur.add(nums[p]);
            find(nums, res, p+1, nums[p]);
            cur.remove(cur.size()-1);

            cur.removeLast();
            cur.add(nums[p]);

            find(nums, res, p+1, prev);

        }




    }
}
