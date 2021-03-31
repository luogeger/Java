package com.first.thread;

/**
 * synchronized 既保证了原子性，也保证了可见性
 */
public class VS implements Runnable{

    private /*volatile*/ int count = 100;

    public synchronized/**/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        VS t = new VS();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();

        }
    }
}
