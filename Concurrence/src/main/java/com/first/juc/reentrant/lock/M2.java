package com.first.juc.reentrant.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁替代Synchronized
 */
public class M2 {

    private Lock lock = new ReentrantLock();

    private void app () {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void ban() {
        try {
            lock.lock();
            System.out.println("ban ..");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        M2 m2 = new M2();
        new Thread(m2::app, "first").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(m2::ban, "second").start();
    }


}
