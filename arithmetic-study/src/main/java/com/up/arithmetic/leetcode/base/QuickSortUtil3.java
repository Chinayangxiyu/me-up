package com.up.arithmetic.leetcode.base;


public class QuickSortUtil3 {

    // [[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]]
    public static void main(String[] args) {
        QuickSortUtil3 q = new QuickSortUtil3();
        int[] array = new int[]{12,7,14,9,10,11};
        q.quicksort(array);

        for(int i = 0; i< array.length; i++){
            System.out.println(array[i]);
        }

    }


    /**
     * 1、从左边找到一个基准 cur；
     * 2、从右边开始查找比这个基准值小的值，找到后标记这个索引 rindex；
     * 3、从左边开始找比基准值大的值，找到后标记索引为lindex，交换rindex和lindex的值；
     * 4、然后从rindex开始继续循环 2、3步骤，直到左右指针碰撞；
     * 5、因为我们是从右边开始的，所以肯定是右边的指针找到一个比基准值小的值为结尾（tailindex）；最后交换tailIndex的值和cur
     */
    public void quicksort(int[] array){

        sort(array, 0, array.length-1);

    }



    private int cur;
    public void sort(int[] array, int left, int right){
        while(left > right){
            return;
        }


        cur = array[left];
        int i = left;
        int j = right;
        while(left < right){
            right = rightSort(array, left, right);
            left = leftSort(array, left, right);
        }


        array[i] = array[right];
        array[left] = cur;

        //【错误点】二分的时候array[left]这个值左边的值肯定小于他，右边的值大于他，所以二分的时候
        // 区间是[i, left-1] [left+1, j]
        sort(array, i, left-1);
        sort(array, left+1, j);

    }


    private int rightSort(int[] array, int left, int right){

        for(int i = right; i > left; i--){
            if(array[i] < cur){
                return i;
            }
        }

        return left;
    }

    private int leftSort(int[] array, int left, int right){


        for(int i = left; i < right; i++){
            // 当arrays[i] > cur的时候，当前比较方法是从左边找的比基准值大的元素；
            // 所以当前元素需要和rightSort()方法返回的元素进行交换
            if(array[i] > cur){
                int temp = array[right];
                array[right] = array[i];
                array[i] = temp;
                return i;

            }
        }

        return right;
    }

}
