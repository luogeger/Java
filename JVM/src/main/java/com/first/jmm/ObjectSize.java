package com.first.jmm;

public class ObjectSize {

    public static void main(String[] args) {
        System.out.println(AgentObjectSize.sizeOf(new Object()));
        System.out.println(AgentObjectSize.sizeOf(new int[]{}));
        System.out.println(AgentObjectSize.sizeOf(new P()));
    }

    //一个Object占多少个字节
    // -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
    // Oops = ordinary object pointers
    private static class P {
        //8 _markword
        //4 _class pointer
        int id;         //4
        String name;    //4
        int age;        //4

        byte b1;        //1
        byte b2;        //1

        Object o;       //4
        byte b3;        //1

    }
}
