package com.first.c.volatiles;

/**
 *
 */
public class M2Reference {

    private static class Data {
        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    volatile static Data data;


    public static void main(String[] args) {
        Thread write = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Data(i, i);
            }
        });


        Thread read = new Thread(() -> {
            while (data == null) {
                //  永远不会结束，data一直为Null
            }
            System.out.println(data.a + " -- " + data.b);
        });

        write.start();
        read.start();

        try {
            write.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");


    }
}
