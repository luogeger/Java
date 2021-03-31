package com.first.volatiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * count++非原子操作，使用synchronized太重，而且这种操作比较常见，
 * jdk内部提供了一些自身带锁的方法，这个锁是基于CAS实现的。
 */
public class M3 {

    private AtomicInteger count = new AtomicInteger();

    private void method() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        M3 m3 = new M3();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(m3::method, "thread-" + i));
        }

        //  线程启动
        threadList.forEach(Thread::start);

        //  线程停止
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(m3.count);
    }



}
