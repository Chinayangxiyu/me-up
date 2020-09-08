package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode51 {


    public static void main(String[] args) {
        leetcode51 l = new leetcode51();
        List<List<String>> lists = l.solveNQueens(4);
        if(lists != null && lists.size() > 0){
            lists.forEach(ls -> {
                ls.forEach(s -> {
                    System.out.println(s);
                });
            });
        }

    }

    /**
     * 一个位置会同时处于四条规则的线上；
     * 横线，竖线，左斜线（左高右低），右斜线（右高左低）
     * 每次遍历，需要判断线上是否已经存在了皇后，知道遍历到最后一个空格
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        // 如果当前横坐标或纵坐标被占了，那么
        crosswise = new boolean[n];
        lengthways = new boolean[n];
        temp = new int[n];

        // 索引0表示[0, n-1]为起点的左斜线（左高右低），索引2n-2表示起点为[n-1,0]的左斜线
        // 如何确定一个格子在哪个左斜线；
        // leftindex = n - 1-x + y;

        // 同上我们需要计算当前单元格在右斜线（左低右高）的位置，并判断当前斜线是否已经存在了皇后；
        // 索引0表示[0,0]为起点的斜线；索引2*n -2表示[n -1, n-1]为起点的斜线；
        // 确定格子在哪一条右斜线
        // rightindex =x + y;

        left = new boolean[2 * n - 1];
        right = new boolean[2 * n - 1];

        model=new char[n];

        for (int i =0; i < n; i++){
            model[i] = '.';
        }
        calc(0, n);

        return queueList;
    }

    public List<List<String>> queueList = new ArrayList<>();
    int[] temp;
    boolean[] left;
    boolean[] right;
    boolean[] crosswise;
    boolean[] lengthways;
    char[] model;


    /**
     *
     * @param y 纵坐标
     * @param n 总长度
     */
    public void calc(int y, int n){

        // x为横坐标
        for(int x = 0; x < n; ){


        int leftindex = n - 1-x + y;
        int rightindex =x + y;

            // 如果四条线上窦不存在元素，则当前格子可以保存皇后
            if(!left[leftindex] && !right[rightindex] && !crosswise[x] && !lengthways[y]){

                // 线上保存皇后
                left[leftindex]=true;
                right[rightindex]=true;
                crosswise[x]=true;
                lengthways[y]=true;

                temp[y] =x;
                // 达到最后一行，说明存在解决方案
                if(y == n-1){
                    // 构建结果
                    build(temp, n);
                }else{
                    // 如果不是最后一行，则递归继续找下一行的结果
                    calc(y + 1, n);
                }
                temp[y] =0;

                // 清空每一条线上的标记
                left[leftindex]=false;
                right[rightindex]=false;
                crosswise[x]=false;
                lengthways[y]=false;
                x++;
            }else{
                x++;
            }

        }
    }


    private void build(int[] array, int n){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            char[] a = Arrays.copyOf(model, model.length);
            a[array[i]] = 'Q';
            list.add(new String(a));

        }
        queueList.add(list);
    }


}
