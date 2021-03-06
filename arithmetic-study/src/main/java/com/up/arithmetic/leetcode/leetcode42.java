package com.up.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author yxy
 * @date 2020/4/27 15:57
 * @description
 */
public class leetcode42 {

    public static void main(String[] args) {

        int[] array = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trapNew(array, 0, array.length-1);
        System.out.println(result);
        int result2 = trap2(array);

        System.out.println(result2);
    }

    // 分析思路，要获取容量就需要获取最大值（first），和第二大值（second）中间的容量；
    // 中间的容量等于 secod一次减去区域内所有的值的“正”结果，
    // 然后以当前first,second为分解，将剩下的未参与计算的元素分离成两个子数组递归计算
    public static int trap(int[] array) {
        return trapNew(array, 0, array.length-1);
    }
    public static int trapNew(int[] array, int start, int end) {

        // 结束计算
        if(end <= start){
            return 0;
        }


        // 当前数组最高的墙，及其索引
        int firstHigth = 0;
        int firstHigthIndex = 0;
        // 第二高的墙及其索引
        int secondHigth = 0;
        int secondHigthIndex = 0;

        // 遍历当前数组，找出最大值和第二大的值
        for( int currentStart = start; currentStart <= end; currentStart ++){
            if( array[currentStart] >= firstHigth){
                secondHigth = firstHigth;
                secondHigthIndex = firstHigthIndex;
                firstHigth = array[currentStart];
                firstHigthIndex = currentStart;
            }else if(array[currentStart] >= secondHigth){
                secondHigth = array[currentStart];
                secondHigthIndex = currentStart;
            }
        }

        // 最高的墙和第二高的墙有为0，表示当前区域内没有积水
        if(secondHigth == 0 || firstHigth == 0){
            return 0;
        }

        // 获取当前区域临界“墙”的左右索引
        int minIndex =  firstHigthIndex < secondHigthIndex ? firstHigthIndex : secondHigthIndex;
        int maxIndex =  firstHigthIndex > secondHigthIndex ? firstHigthIndex : secondHigthIndex;
        // 根据区域左右索引，第二高的墙，计算当前区域内的积水
        int currentCapacity = calcCapacity(array, minIndex, maxIndex, secondHigth);

        // 计算当前区域左边剩余区域的积水
        int left = trapNew( array, start, minIndex);
        // 计算当前区域右边区域剩余的积水
        int right = trapNew( array, maxIndex , end );



        return left + right + currentCapacity;
    }

    // 计算区域内积水
    public static int calcCapacity(int[] array, int start, int end, int secondHigth){
        int capacity = 0;
        // start、end为边界值，不参与计算
        for(int i = start +1; i < end;  i++){
            // 每格的积水等于secondHigth - 当前墙的高度
            capacity += secondHigth - array[i];
        }

        return capacity;
    }

    public static int trap2(int[] array) {

        if(array == null || array.length <2){
            return 0;
        }
        // 存当前最大值得栈
        Stack<Integer> maxLeft = new Stack<>();
        // 辅助栈，存当前区域内得墙的高度
        Stack<Integer> capacitys = new Stack<>();

        // 初始化
        maxLeft.push(array[0]);
        capacitys.push(array[0]);
        int total = 0;
        // 遍历数组
        for(int i = 0; i< array.length; i++){
            // 如果当前值比栈顶值大，
            if(array[i] >= maxLeft.peek()){
                // 计算当前区域积水
                total = calc(capacitys, maxLeft.peek(), total);
                // 因为当前值是目前出现的最大值，压入栈顶
                maxLeft.push(array[i]);
                // 压入辅助栈，下次计算需要
                capacitys.push(array[i]);
            }else{
                // 当前值比栈顶值小，直接压入辅助栈
                capacitys.push(array[i]);
            }
        }

        // 遍历结束maxLeft栈顶的值是数组最大值；如果此时capacitys内部的元素
        // 大于两个（最大值的索引 < length -2），既最大值的位置的右侧可能存在积水；
        // 需要从右向左开始计算积水
        if(capacitys.size() > 2){
            // 从右向左计算的辅助栈
            Stack<Integer> rightTmpStack = new Stack<>();
            // 初始化
            int rightMax = capacitys.pop();
            rightTmpStack.push(rightMax);
            // 遍历从左向右计算的辅助栈。
            while(!capacitys.isEmpty()){
                // 弹出从右向左的元素
                int current = capacitys.pop();
                // 当前值比此时出现的最大值还大，计算积水
                if(current > rightMax){
                    total = calc(rightTmpStack, rightMax, total);
                    // 更新最高墙
                    rightMax = current;
                    // 压入辅助栈
                }else{
                    rightTmpStack.push(current);
                }
            }
        }
        return total;
    }

    // 计算当前区域积水
    public static int calc(Stack<Integer> capacitys, int min, int total){
        while(!capacitys.isEmpty()){
            total += (min - capacitys.pop());
        }
        return total;
    }
}
