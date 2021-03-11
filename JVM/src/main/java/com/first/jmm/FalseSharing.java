package com.first.jmm;

/**
 * 伪共享： 两个无关数据位于同一缓存行，相互影响而降低了效率
 */
public class FalseSharing {

    /**
     * 继承了Padding， 效率能提高
     */
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }


    private static class Inner extends Padding{
        volatile long x = 0L;
    }

    private static Inner[] arr = new Inner[2];

    static {
        arr[0] = new Inner();
        arr[1] = new Inner();
    }


    public static void main(String[] args) throws InterruptedException {
        long count = 10_000_000L;

        Thread t0 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                arr[0].x = 1;
            }
        });
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                arr[1].x = 1;
            }
        });

        final long start = System.nanoTime();
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.println((System.nanoTime() - start)/100_000L);


    }

}
