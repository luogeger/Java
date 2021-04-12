package com.first.d.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 *
 */
public class M7Latch {

    private volatile List<Integer> list = new ArrayList<>();

    private void add(Integer i) {
        list.add(i);
    }

    private int size() {
        return list.size();
    }


    public static void main(String[] args) {
        M7Latch m = new M7Latch();
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch1 = new CountDownLatch(1);

        new Thread(()-> {
            System.out.println("add start");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                m.add(i);
                if (m.size() == 5) {
                    latch.countDown();//  countDown以后线程并不会停下来。
                    try {
                        latch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("add end");
        }, "add").start();




        new Thread(()-> {
            System.out.println("monitor start");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("monitor end");
            latch1.countDown();
        }, "monitor").start();



    }
}
