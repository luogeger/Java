package com.company.g_thread;

import org.junit.Test;

public class ThreadTest {

    public static void main(String[] args) {
        //newThread1();
        //newThread2();
        //newThread3();
        sellTicketTask1();





    }


    /**
     * 多线程执行卖票任务
     */
    private static void sellTicketTask1() {
        SellTicketTask t = new SellTicketTask();//
        new Thread(t, "t1").start();
        new Thread(t, "t2").start();
        new Thread(t, "t3").start();
        new Thread(t, "t4").start();
    }

    /**
     * 创建线程3：一次性线程
     */
    private static void newThread3() {
        /** 创建一次性线程*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() +"\t"+ i);// Thread-0
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +"\t"+ i);// main
        }
    }


    /**
     * 创建线程2：实现Runnable接口，重写run()
     */
    private static void newThread2() {
        MyThread mt = new MyThread();
        mt.start();// 开辟线程，执行run(),
        Thread t = Thread.currentThread();//获取当前线程的对象
        t.setName("main");
        for (int i = 0; i < 20; i++) {
            System.out.println(t.getName() +"\t--"+ i);
        }
    }


    /**
     * 创建线程1：继承Thread类
     */
    private static void newThread1() {
        MyThread mt = new MyThread();
        mt.start();// 开辟线程，执行run(),

        //MyThread mt2 = new MyThread();
        //mt2.start();
        Thread t = Thread.currentThread();//获取当前线程的对象
        t.setName("main");
        for (int i = 0; i < 10; i++) {
            System.out.println(t.getName() +"\t--"+ i);
        }
    }


    @Test
    public void test1 () {
        MyThread mt = new MyThread();
        mt.start();// tip: 必须在main方法里面执行，main是主线程，@Test不能开辟线程


        for (int i = 0; i < 10; i++) {
            System.out.println(i+"--");
        }
    }


}
