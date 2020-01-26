package com.first.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        t.setName("MyRunnable");

        for (int i = 0; i < 10; i++) {
            System.out.println(t.getName() +"\t"+ i);
        }
    }
}
