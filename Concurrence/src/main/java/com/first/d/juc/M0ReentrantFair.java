package com.first.d.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认是非公平锁
 */
public class M0ReentrantFair extends Thread{

    /**
     * 公平锁：交替输出
     */
     private static ReentrantLock lock = new ReentrantLock(true);
    /**
     * 默认非公平，t1线程全部输出后，t2再输出
     */
    // private static ReentrantLock lock = new ReentrantLock();

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
        M0ReentrantFair entity = new M0ReentrantFair();
        Thread th1 = new Thread(entity, "A");
        Thread th2 = new Thread(entity, "B");
        th1.start();
        th2.start();
    }
}
