package com.up.jdk.juc;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * TODO
 * Description
 *
 * @author xiyu
 * @date 2021-02-03 18:51
 */
public class ConcurrentSkipListMapDemo {

    public static void main(String[] args) {
        ConcurrentSkipListMap map = new ConcurrentSkipListMap();
        map.put(1, 2);
    }
}
