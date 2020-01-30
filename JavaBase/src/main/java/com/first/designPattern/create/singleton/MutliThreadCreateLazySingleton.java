package com.first.designPattern.create.singleton;

/**
 * @author luoxiaoqing
 * @date 2018-01-10__17:24
 * @desc
 */
public class MutliThreadCreateLazySingleton implements Runnable {
    @Override
    public void run() {
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+" "+ instance);
        System.out.println();
    }


}
