package com.first.thread.cache;


/**
 * 利用缓存行， 64bit
 */
public class M2 {

    private static volatile long[] arr = new long[16];

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                arr[0] = i;
            }
        });


        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                //  注意： 这里是8
                arr[8] = i;
            }
        });

        final long start = System.nanoTime();

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}
