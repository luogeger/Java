package com.first.d.juc;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 一个或多个变量共同保持初始为零的长和。当跨线程竞争更新（方法添加）时，变量集可能会动态增长以减少争用。
 * 方法sum（或等效地，longValue）返回保持变量总和的所有变量的当前总和。
 * 当多个线程更新用于诸如收集统计信息之类的目的而不是用于细粒度的同步控制的公共和时，此类通常比AtomicLong更可取。
 * 在低更新争用下，这两个类具有相似的特征。但是在竞争激烈的情况下，此类的预期吞吐量会大大提高，但要消耗更多的空间。
 * LongAdders可以与java.util.concurrent.ConcurrentHashMap一起使用，以维护可扩展的频率图（一种直方图或多集形式）。
 * 例如，要将计数添加到ConcurrentHashMap <String，LongAdder>频率中，如果还不存在，则进行初始化，
 * 可以使用freqs.computeIfAbsent（k-> new LongAdder（））。increment（）;。
 * 此类扩展了Number，但未定义equals，hashCode和compareTo之类的方法，
 * 因为预计实例将发生突变，因此不能用作集合键。
 */
public class M0LongAdder {
    private static long count = 0L;
    private static AtomicLong countAtomic = new AtomicLong(0L);
    private static LongAdder countAdder = new LongAdder();

    public static void main(String[] args) throws Exception {
        main2();
    }

    private static void main1() throws Exception {

        Thread[] threads = new Thread[1000];

        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++)
                    synchronized (lock) {
                        count++;
                    }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.println("Sync: " + count + " time " + (end - start));


        // =================================================================================================================

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    countAtomic.incrementAndGet();
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        //TimeUnit.SECONDS.sleep(10);
        System.out.println("Atomic: " + countAtomic.get() + " time " + (end - start));

        // =================================================================================================================

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    countAdder.increment();
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.println("LongAdder: " + countAtomic.longValue() + " time " + (end - start));
    }


    private static void main2() throws Exception {
        Thread[] threads = new Thread[500];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) countAdder.increment();
            });
        }

        long start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        //TimeUnit.SECONDS.sleep(10);
        System.out.println("LongAdder: " + countAdder.longValue() + " time " + (end - start));

        // =================================================================================================================

        Object lock = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    synchronized (lock) {
                        count++;
                    }
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.println("Sync: " + count + " time " + (end - start));
    }

}
