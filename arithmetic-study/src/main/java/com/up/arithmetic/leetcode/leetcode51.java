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
     * 拿到这四条线，查找是否已经存在了皇后，如果存在，就递归下一个格子。
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
        // 1、纵坐标（x,y)， n-x > n-y 说明在右半区，既起点是横坐标;
        // 找到起点的坐标[px, py] = (0, y-x), 找到坐标后，根据坐标找到数组left 对应位置的标记
        // 对于起点为横坐标的，索引计算方式 index = n - 1 -py = n - 1 - y +x
        // 2、n-x == n-y 既起点是[0,0]
        // 所以index索引 为 n-1
        // 3、n-x < n-y 既起点是纵坐标
        // 起点为纵坐标，起点坐标[px, py]=[x-y , 0]；找到起点坐标后，需要找到left数组对应标记
        // index = n - 1 + py =  = n -1 + x -y

        // 同上我们需要计算当前单元格在右斜线（左低右高）的位置，并判断当前斜线是否已经存在了皇后；
        // 索引0表示[0,0]为起点的斜线；索引2*n -2表示[n -1, n-1]为起点的斜线；
        // 根据格子坐标(x,y)判断其右斜线起点，，x==y说明格子在中线
        // 1、x > y说明格子在左半区，起点坐标[px, py] = [0, x]
        // 对应right数组的索引index = py = x上的标记
        // 2、x==y，说明格子在中线，起点坐标[px, py] = [0, n-1]
        // 对应right数组索引index = n-1位置上的标记
        // 3、x < y,说明格子在右半区，起点坐标[px, py] = [y, n-1]
        // 对应right数组索引index = n-1 + px = n-1 + y
        left = new boolean[2 * n - 1];
        right = new boolean[2 * n - 1];

        calc(0, n);

        return queueList;
    }

    public List<List<String>> queueList = new ArrayList<>();
    int[] temp;
    boolean[] left;
    boolean[] right;
    boolean[] crosswise;
    boolean[] lengthways;

    /**
     *
     * @param y 纵坐标
     * @param n 总长度
     */
    public void calc(int y, int n){

        // x为横坐标
        for(int x = 0; x < n; ){

//            int leftindex;
//            int rightindex;
            // 根据之前的推论，根据(x,y)的坐标值获取所在线的索引

//            if(x < y){
//                leftindex = n - 1 - y +x;
//            }else if(x > y){
//                leftindex = n - 1 + x -y;
//            }else {
//                leftindex = n-1;
//            }

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
        char[] t = new char[n];
        for (int i =0; i < n; i++){
            t[i] = '.';
        }

        List<String> list = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            char[] a = Arrays.copyOf(t, t.length);
            a[array[i]] = 'Q';
            list.add(new String(a));

        }

        queueList.add(list);
    }


}
