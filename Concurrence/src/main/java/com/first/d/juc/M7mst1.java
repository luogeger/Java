package com.first.d.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class M7mst1 {

    private Lock lock = new ReentrantLock();
    private static final List<String> letter = Arrays.asList("A", "B", "C", "D", "E");
    private static final List<String> number = Arrays.asList("1","2", "3", "4", "5");
    private static final int length = 5;
    private static AtomicInteger index = new AtomicInteger(0);

    void printLetter() {
        lock.lock();
        while (length < index.get()) {
            System.out.println(letter.get(index.get()));
            index.incrementAndGet();
            lock.unlock();
        }
    }

    void printNumber() {
        lock.lock();
        while (length < index.get()) {
            System.out.println(number.get(index.get()));
            index.incrementAndGet();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        M7mst1 entity = new M7mst1();
        new Thread(entity::printLetter, "first").start();
        new Thread(entity::printNumber, "second").start();
    }
}
