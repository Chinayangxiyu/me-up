package com.up.book.jksj;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SerializableTest {


    // 枚举特性，单例模式


    public static void main(String[] args) {
//        try {
//            writeByStream();
//            writeByBuffer();
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        User user = new User();
        user.setUserName(name);
        user.setPassword(password);

        Class clazz = user.getClass();
        Field[] fields = clazz.getDeclaredFields();



    }


    public static final String name="请问而儿童而他的接口内置上岛咖啡尼克斯队不分开1啊啊苏丹诺夫可是不敢开始什么都不放手";
    public static final String password="请问而儿童而他的接口内置上岛咖啡尼克斯队不分开1啊啊苏丹诺夫可是不敢开始什么都不放手";
    public static void writeByStream() throws Exception{

        User user = new User();
        user.setUserName(name);
        user.setPassword(password);

        ByteArrayOutputStream os =new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(user);

        byte[] testByte = os.toByteArray();

        String str = new String(testByte);
        System.out.print("ObjectOutputStream 字节编码长度：" + testByte.length + "\n");
        System.out.print("ObjectOutputStream string：" + str);
    }


    public static void writeByBuffer() throws Exception{


        ByteBuffer byteBuffer = ByteBuffer.allocate( 2048);

        User user = new User();

        user.setUserName(name);
        user.setPassword(password);


        byte[] userName = user.getUserName().getBytes();
        byte[] password = user.getPassword().getBytes();
        byteBuffer.putInt(userName.length);
        byteBuffer.put(userName);
        byteBuffer.putInt(password.length);
        byteBuffer.put(password);

        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        System.out.print("ByteBuffer 字节编码长度：" + bytes.length+ "\n");

    }




}



class User implements Serializable {

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object readResolve(){

        return null;
    }

}