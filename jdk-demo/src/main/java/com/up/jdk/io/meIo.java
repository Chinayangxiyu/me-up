package com.up.jdk.io;

import java.io.*;

/**
 * @author yxy
 * @date 2020/4/24 11:29
 * @description
 */
public class meIo {

    public static void main(String[] args) {

        PipedInputStream pis = new PipedInputStream();

//
//        ByteArrayOutputStream reader = new ByteArrayOutputStream(pis);
//        reader.toByteArray();
//
//        ByteArrayInputStream

//        Reader reader = new FileReader();Reader();
//        BufferedReader reader1 = new BufferedReader();
//        CharArrayReader charArrayReader = new CharArrayReader()
//        charArrayReader.read();
                FileWriter fileWriter = new FileWriter();
                FileReader fileReader = new FileReader();
        fileWriter.write();

        InputStreamReader inputStreamReader = new InputStreamReader();
        BufferedReader bufferedReader = new BufferedReader();
        PrintWriter printWriter = new PrintWriter();
        StringReader stringReader = new StringReader("aaaaa");
        stringReader.read();
        StringWriter stringWriter = new StringWriter();
        stringWriter.write();

    }
}
