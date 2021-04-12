package com.first.a.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile保证了可见性，但是不能保证原子性
 */
public class M2 {

    private volatile int count = 0;


    public /*synchronized*/ void method() {
        for (int i = 0; i < 10000; i++) {
            //  count++不是原子性的，同时有两个线程读取到值为1，A线程++完以后赋值=2；B线程++完以后还是赋值=2；
            count++;
        }
    }

    public static void main(String[] args) {
        M2 m2 = new M2();
        List<Thread> pool = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pool.add(new Thread(m2::method, "thread-" + i));
        }


        //  线程启动
        pool.forEach(Thread::start);
//          pool.forEach(Thread::run);// 为什么用run方法结果是10000

        //  线程停止
        pool.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(m2.count);
    }
}
