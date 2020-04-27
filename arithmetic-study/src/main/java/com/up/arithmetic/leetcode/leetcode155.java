package com.up.arithmetic.leetcode;

import java.util.Stack;

/**
 * @author yxy
 * @date 2020/4/26 10:09
 * @description
 */
public class leetcode155 {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        int p = stack.top();
        System.out.println(p);
        System.out.println(stack.getMin());
    }
}


/*
 * @description:
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
push(x) —— 将元素 x 推入栈中。
pop() —— 删除栈顶的元素。
top() —— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。

小星星：一定要明确栈的特性，
    栈只能操作栈顶那一个元素，其它位置的元素无法查看、操作。

分析
    （1）栈的实现可以基于数组和链表，知道其中原理就行；
    （2）本题的考点在于以常数时间复杂度获取最小元素；
    （3）我们需要一个容器保存最小元素，保证栈中最小弹出后，容器中的第二大元素顶替它；并且能以O(1)复杂度获取当前最小值；
    （4）可以使用另一个栈（辅助栈）保存当前压入的最小值，值压入的时候，如果值不是最小值则不需要压入；这样辅助栈栈顶肯定是最小的值
    ，弹出的时候将弹出的值和辅助栈比较，一致的话 都执行弹出。
 */
class MinStack {

    Stack<Integer>  stack;
    Stack<Integer>  minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack();
        minStack = new Stack();
    }

    /*
     * @description: 压入的时候，x和minStack栈顶元素比较；如果比它还小则将其压入minStack；
     * 以此也保证的minStack栈顶元素一直是最小的那个值；可以O(1)获取
     */
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty()){
            minStack.push(x);
        }else{
            int currentMin = minStack.peek();
            if(currentMin >= x){
                minStack.push(x);
            }
        }
    }


    public void pop() {
        int topValue = stack.pop();
        if(topValue == minStack.peek()){
            minStack.pop();
        }

    }

    // 弹出的时候，和minStack栈顶元素比较，如果较小的话直接
    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}