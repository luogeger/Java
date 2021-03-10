package com.first.classLoader;

public class Initializing {

//    public static Initializing t = new Initializing();//  不new对象，就不执行初始化代码。
    public static int count = 2;

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("初始化代码  在构造器之前执行");
    }

    public Initializing() {
        System.out.println("构造方法");
        count++;
    }

    public static void main(String[] args) {
        System.out.println(Initializing.count);
    }
}
