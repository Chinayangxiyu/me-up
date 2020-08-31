package com.up.arithmetic.leetcode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode841 {

    public static void main(String[] args) {
    }


    /**
     * 1、一纬数组是房间号，二维数组是钥匙串
     *
     * 从零号房间开始进入房间拿到钥匙串；
     * 需要标记已经进去过的房间，不需要再进去；
     * 最终判断进过的房间总数
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        if(rooms.size() == 1){
            return true;
        }

        // 零号房
        long record = 1 << rooms.size()-1;
        record = find(0, rooms, record);
        long max = 0;
        for(int i = rooms.size()-1; i >=0; i--){
            max |= 1 << i;
        }

        return (record & max) == max;

    }

    private long temp = 0;
    private long find(int roomNum, List<List<Integer>> rooms, long record){

        List<Integer> keys = rooms.get(roomNum);
        if(keys.size() == 0 ){
            return record;
        }

        int i = 0;
        while(i < keys.size()){

            temp = 1 << (rooms.size() - keys.get(i)-1);
            // 当前房间还没进去过
            if((temp & record) != temp){
                record |= temp;
                record = find(keys.get(i), rooms, record);
            }

            i++;
        }
        return record;
    }


    private byte[] tempArray;
    private int total=0;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size() == 1){
            return true;
        }
        tempArray = new byte[rooms.size()];

        // 零号房
        tempArray[0] = 1;
        total +=1;
        find(0, rooms);
        return total == rooms.size();

    }

    private void find(int roomNum, List<List<Integer>> rooms){

        List<Integer> keys = rooms.get(roomNum);
        if(keys.size() == 0 ){
            return;
        }

        if(total == rooms.size()){
            return;
        }

        int i = 0;
        while(i < keys.size()){

            if(tempArray[keys.get(i)] == 0){
                tempArray[keys.get(i)] =1;
                total += 1;
                find(keys.get(i), rooms);
            }

            i++;
        }
        return ;
    }

}
