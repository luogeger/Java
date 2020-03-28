package com.first.design.pattern.create.singleton;

public class LazySingleton {
    /**
     * 1. 懒汉模式，在初始化的是并不立即执行，而是做一个延迟加载
     * 2. 线程是不安全的, 需要通过加锁
     *      a. 同步锁非常消耗资源，加锁解锁影响效率, synchronized加在static锁是这个class文件的，如果没有static就是在堆内存中的
     *
     */



    private static LazySingleton lazySingleton = null;


    /**
     * 构造器一定是 private, 不让外部new
     */
    private LazySingleton () {

    }

    public synchronized static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }

        return lazySingleton;
    }

}





















