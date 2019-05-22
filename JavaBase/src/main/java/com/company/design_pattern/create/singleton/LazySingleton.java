package com.company.design_pattern.create.singleton;

public class LazySingleton {
    // 懒汉模式，在初始化的是并不立即执行，而是做一个延迟加载
    // 构造器一定是 private, 不让外部new
    // 线程是不安全的

    /**
     * 解决方案：
     * 1. 同步锁，但是同步锁非常消耗资源，加锁解锁影响效率
     * 2.
     * */

    private static LazySingleton lazySingleton = null;

    private LazySingleton () {

    }

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }

        return lazySingleton;
    }

}
