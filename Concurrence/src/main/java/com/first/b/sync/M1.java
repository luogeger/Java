package com.first.b.sync;

import java.util.concurrent.TimeUnit;

/**
 * 多线程争用同一个锁
 */
public class M1 {

    private synchronized void app() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(i);
            if (i == 2) ban();
        }
    }


    private synchronized void ban() {
        System.out.println("ban ..........");
    }

    public static void main(String[] args) {
        M1 m1 = new M1();
        new Thread(m1::app, "first").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(m1::ban, "second").start();
    }


}
