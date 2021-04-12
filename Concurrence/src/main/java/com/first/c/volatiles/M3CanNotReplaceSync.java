package com.first.c.volatiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile不能代替Synchronized
 */
public class M3CanNotReplaceSync {

    //private volatile int count;
    private AtomicInteger count = new AtomicInteger(0);

    private void run () {
        for (int i = 0; i < 10000; i++) {
            //count++;
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        M3CanNotReplaceSync entity = new M3CanNotReplaceSync();
        List<Thread> pool = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            pool.add(new Thread(entity::run, "name - "+ i));
        }

        pool.forEach(Thread::start);

        pool.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //  即使count被volatile修饰，但是count++不是原子操作，所以最终结果会小于10W
        System.out.println(entity.count);
    }


}
