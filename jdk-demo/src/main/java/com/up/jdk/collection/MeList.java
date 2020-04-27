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
        ArrayList<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }



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
