package com.first.jmm;

/**
 * 伪共享
 */
public class FalseSharing {

    private static class Inner {
        volatile long x = 0L;
    }

    private static Inner[] arr = new Inner[2];

    static {
        arr[0] = new Inner();
        arr[1] = new Inner();
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            for (long i = 0; i < 100_000L; i++) {
                arr[0].x = 1;
            }
        });
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 100_000L; i++) {
                arr[1].x = 1;
            }
        });

        final long start = System.nanoTime();
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.println(System.nanoTime() - start);


    }

}
