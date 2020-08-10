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


    // ö�����ԣ�����ģʽ


    public static void main(String[] args) {
//        try {
//            writeByStream();
//            writeByBuffer();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//
//        User user = new User();
//        user.setUserName(name);
//        user.setPassword(password);
//
//        Class clazz = user.getClass();
//        Field[] fields = clazz.getDeclaredFields();


        double d1 = 12d;
        double d2 = 0.0;

        System.out.println("--------");
        System.out.println((int)d1/d2);
        System.out.println((int)d1/0.00f);
        System.out.println((byte)d1/0.00f);

    }


    public static final String name="���ʶ���ͯ�����Ľӿ������ϵ��������˹�Ӳ��ֿ�1�����յ�ŵ����ǲ��ҿ�ʼʲô��������";
    public static final String password="���ʶ���ͯ�����Ľӿ������ϵ��������˹�Ӳ��ֿ�1�����յ�ŵ����ǲ��ҿ�ʼʲô��������";
    public static void writeByStream() throws Exception{

        User user = new User();
        user.setUserName(name);
        user.setPassword(password);

        ByteArrayOutputStream os =new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(user);

        byte[] testByte = os.toByteArray();

        String str = new String(testByte);
        System.out.print("ObjectOutputStream �ֽڱ��볤�ȣ�" + testByte.length + "\n");
        System.out.print("ObjectOutputStream string��" + str);
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
        System.out.print("ByteBuffer �ֽڱ��볤�ȣ�" + bytes.length+ "\n");

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