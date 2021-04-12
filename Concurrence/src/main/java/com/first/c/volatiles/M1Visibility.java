package com.first.c.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 保证线程之间的可见性
 */
public class M1Visibility {

    /*volatile*/ boolean flag = true;

    private void run() {
        System.out.println("start");
        while (flag) {

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        M1Visibility entity = new M1Visibility();
        Thread thread = new Thread(entity::run);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        entity.flag = false;

    }

}
