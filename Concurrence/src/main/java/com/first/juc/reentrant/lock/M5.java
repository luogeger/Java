package com.first.juc.reentrant.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认是非公平锁
 */
public class M5 extends Thread{

    private static ReentrantLock lock = new ReentrantLock();

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " -- "+ i);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        //  两个线程执行同样的代码；分别循环100次；
        M5 rl = new M5();
        Thread th1 = new Thread(rl, "A");
        Thread th2 = new Thread(rl, "B");
        th1.start();
        th2.start();
    }
}
