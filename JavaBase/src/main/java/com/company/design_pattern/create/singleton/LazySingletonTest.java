package com.company.design_pattern.create.singleton;

public class LazySingletonTest {
    public static void main(String[] args) {
        System.out.println(args);
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(instance);


    }
}
