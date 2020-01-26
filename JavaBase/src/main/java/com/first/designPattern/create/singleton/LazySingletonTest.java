package com.first.designPattern.create.singleton;

public class LazySingletonTest {

    public static void main(String[] args) {
        System.out.println(args);
//        LazySingleton instance = LazySingleton.getInstance();
//        System.out.println(instance);

        //  多线程使用懒汉单例模式
        Thread t1 = new Thread(new MutliThreadCreateLazySingleton());
        Thread t2 = new Thread(new MutliThreadCreateLazySingleton());
        t1.start();
        t2.start();
        System.out.println("end");
    }


}
