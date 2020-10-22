package com.first.thread.voa;


/**
 * 乱序
 */
public class Disorder {

    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;


            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                b = 1;
                y = a;
            });

            one.start();
            two.start();
            one.join();
            two.join();

            String result = "第" + i + "次执行： x=" + x + "，y=" + y;

            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            }
        }
    }
}
