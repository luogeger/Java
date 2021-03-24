package com.first.gc;

public class DeadLock {


    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println("Hello Java -> " + i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}