package com.first.a.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替输出
 */
public class M9AlternatePrint {
    private static List<String> number = Arrays.asList("1", "2", "3", "4", "5");
    private static List<String> letter = Arrays.asList("A", "B", "C", "D", "E");
    private static int length = 5;
    private static AtomicInteger index = new AtomicInteger(0);
    private static String threadLetter = "letter";
    private static String threadNumber = "number";
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(M9AlternatePrint::print, threadLetter).start();
        new Thread(M9AlternatePrint::print, threadNumber).start();

    }

    private static void print() {
        for (int i = 0; i < length; i++) {
            lock.unlock();
            try {
                String name = Thread.currentThread().getName();
                List<String> list = threadLetter.equalsIgnoreCase(name) ? letter : number;
                System.out.println(name + " -- " + list.get(i));
            } finally {

                lock.unlock();
            }
        }

    }


}
