package com.first.juc;

import java.util.ArrayList;
import java.util.List;

public class M7LockSupport1 {
    private volatile List<Integer> list = new ArrayList<>();

    private void add(Integer i) {
        list.add(i);
    }

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {
        M7LockSupport1 m = new M7LockSupport1();

        Thread add = new Thread(() -> {
            System.out.println("add start");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                m.add(i);
                if (m.size() == 5) {

                }
            }
            System.out.println("add end");
        }, "add");


        Thread monitor = new Thread(() -> {
            System.out.println("monitor start");

            System.out.println("monitor end");
        }, "monitor");



    }
}
