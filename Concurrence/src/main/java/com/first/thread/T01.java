package com.first.thread;

public class T01 {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();

        System.out.println(t.getState());

        t.start();

        try {
            t.join();// 这里的main线程是WAIT状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("main: "+t.getState());

    }
}
