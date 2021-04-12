package com.first.d.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁替代Synchronized
 */
public class M0Reentrant {

    private Lock lock = new ReentrantLock();

    void app () {
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

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void ban() {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(3);
            System.out.println("ban ..");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        M0Reentrant entity = new M0Reentrant();
        new Thread(entity::app, "first").start();

        //TimeUnit.SECONDS.sleep(1);

        new Thread(entity::ban, "second").start();
    }


}
