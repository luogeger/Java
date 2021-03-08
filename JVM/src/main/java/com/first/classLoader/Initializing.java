package com.first.classLoader;

public class Initializing {

    public static Initializing t = new Initializing();
    public static int count = 2;

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("初始化代码");
    }

    public Initializing() {
        System.out.println("构造方法");
        count++;
    }

}
