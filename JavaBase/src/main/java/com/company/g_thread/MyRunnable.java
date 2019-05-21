package com.company.g_thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();//获取当前线程的对象
        t.setName("MyRunnable");

        for (int i = 0; i < 10; i++) {
            System.out.println(t.getName() +"\t"+ i);
        }
    }
}
