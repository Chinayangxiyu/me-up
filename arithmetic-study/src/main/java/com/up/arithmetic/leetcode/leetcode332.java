package com.up.arithmetic.leetcode;

import java.util.*;

public class leetcode332 {

    public static void main(String[] args) {

        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));

        leetcode332 l =new leetcode332();
        l.findItinerary(tickets);

    }

    /**
     * PriorityQueue比，因为要保证排序；并且顺序在前面的要先弹出，PriorityQueue根据字符串排序，排序优先的在队列头部
     * queue
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> map = new HashMap<>((int)(tickets.size()/ 0.75));
        List<String> temp = new ArrayList<>(tickets.size() + 1);

        for(int i = 0; i < tickets.size(); i++){
            PriorityQueue<String> vs = map.get(tickets.get(i).get(0));
            if(vs == null){
                PriorityQueue queue = new PriorityQueue();
                queue.offer(tickets.get(i).get(1));
                map.put(tickets.get(i).get(0), queue);
                continue;
            }

            vs.offer(tickets.get(i).get(1));
        }

        build("JFK", map, temp);
        Collections.reverse(temp);
        return temp;

    }

    public void build(String cur, Map<String, PriorityQueue<String>> map, List<String> temp){


        //不想最终反转temp的思路，但是因为是正向添加节点，所以不能判断next之后的路是不是死路；
        // 最终会导致无法找到通路；
//        if(map.containsKey(cur) && map.get(cur).size() > 0){
//            for(int i = 0; i < map.get(cur).size(); i++){
//                String next = map.get(cur).poll();
//                temp.add(next);
//                build(next);
//            }
//        }

        // 我们需要先判断这条路是通的，再将节点添加到temp；每次传进来的cur判断它之后的路是否为死路；
        // 不是的话继续往下走，直到找到终点；然后再逆向将节点添加到temp
        while(map.containsKey(cur) && map.get(cur).size() > 0){
            String next = map.get(cur).poll();
            build(next, map, temp);
        }

        temp.add(cur);
    }

}
