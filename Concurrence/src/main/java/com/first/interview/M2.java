package com.first.interview;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 交替输出
 */
public class M2 {
    private static final List<String> letter = Arrays.asList("A", "B", "C");
    private static final List<Integer> number = Arrays.asList(1, 2, 3);
    private static final int length = 2;
    private static AtomicInteger index = new AtomicInteger(0);

    public static void main(String[] args) {

        new Thread(() -> {
            if (index.get() < length) {
                print(letter, index);
                //  停下来，再等待被唤醒

                index.incrementAndGet();
            }
        }).start();

        new Thread(() -> {
            if (index.get() < length) {
                print(number, index);

                index.incrementAndGet();
            }
        }).start();


    }

    private static synchronized void print(List list, AtomicInteger index) {
        System.out.println(list.get(index.get()));
    }
}
