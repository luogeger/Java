package com.first.juc.reentrant.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock可以尝试加锁，如果在一段时间内，加锁失败，就放弃
 *
 * 如果锁在给定的等待时间内是空闲的，并且当前线程尚未中断，则获取该锁。
 * 如果锁可用，则此方法立即返回true值。如果该锁不可用，则出于线程调度目的，当前线程将被禁用，并在发生以下三种情况之一之前处于休眠状态：
 * 1. 该锁是由当前线程获取的；或者
 * 2. 其他一些线程中断当前线程，并支持中断获取锁。或者
 * 3. 经过指定的等待时间
 *
 * 如果获取了锁，则返回值true。
 * 如果当前线程：
 *
 * 在进入此方法时已设置其中断状态；或者
 * 获取锁时被中断，并且支持中断获取锁，
 * 然后抛出InterruptedException并清除当前线程的中断状态。
 * 如果经过了指定的等待时间，则返回值false。如果时间小于或等于零，则该方法将根本不等待。
 * 实施注意事项
 * 在某些实现中，中断锁获取的能力可能是不可能的，并且如果可能的话可能是昂贵的操作。程序员应意识到可能是这种情况。在这种情况下，实现应记录在案。
 * 与正常方法返回或报告超时相比，实现可能更喜欢对中断做出响应。
 * 锁实现可能能够检测到锁的错误使用，例如可能导致死锁的调用，并且在这种情况下可能引发（未经检查的）异常。该Lock实现必须记录情况和异常类型。
 */
public class M3 {

    private Lock lock = new ReentrantLock();

    private void app() {
        try {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void ban() {
        boolean locked = false;

        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }

    }

    public static void main(String[] args) {
        M3 m3 = new M3();
        new Thread(m3::app).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(m3::ban).start();
    }
}
