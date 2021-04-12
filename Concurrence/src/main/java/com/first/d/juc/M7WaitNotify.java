package com.first.d.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class M7WaitNotify {

    //添加volatile，使t2能够得到通知
    private volatile List<Object> lists = new ArrayList<>();

    private void add(Object o) {
        lists.add(o);
    }

    private int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        M7WaitNotify c = new M7WaitNotify();

        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();//  唤醒t1
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();// 必须wait才能释放锁，notify不能释放锁
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
            System.out.println("t1结束");
        }, "t1").start();






    }
}
