package com.company.thread;

public class MyThread extends Thread {

    @Override
    public void run() {
        //这里就是线程要执行的代码
        Thread t = Thread.currentThread();
        t.setName("MyThread");

        for (int i = 0; i < 10; i++) {
            System.out.println(t.getName() +"\t"+ i);
        }
    }
}
