package com.up.jdk.collection;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author yxy
 * @date 2020/4/22 15:38
 * @description
 */
public class MeList {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("str", "value");

        map.get("star");

        HashSet set = new HashSet();

    }

    private void testVector(){
        Vector<String> vector = new Vector();

        vector.add(null);


        Stack stack = new Stack();
        stack.add("aa");

        stack.push("bb");
        stack.pop();

    }
}
