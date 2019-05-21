package com.company.g_thread;

public class MyThread extends Thread {
    public void run() {
        //这里就是线程要执行的代码
        Thread t = Thread.currentThread();//获取当前线程的对象
        t.setName("MyThread");

        for (int i = 0; i < 10; i++) {
            System.out.println(t.getName() +"\t"+ i);
        }
    }
}
