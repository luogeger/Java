package com.first.d.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Synchronized一旦wait以后，必须让别人notify才能醒来。
 *
 *
 * 除非当前线程被中断，否则获取锁。
 * 获取锁（如果有）并立即返回。
 * 如果该锁不可用，则出于线程调度目的，当前线程将被禁用，并在发生以下两种情况之一之前处于休眠状态：
 * 该锁是由当前线程获取的；或者
 * 其他一些线程会中断当前线程，并支持中断获取锁。
 * 如果当前线程：
 * 在进入此方法时已设置其中断状态；或者
 * 获取锁时被中断，并且支持中断获取锁，
 * 然后抛出InterruptedException并清除当前线程的中断状态。
 * 实施注意事项
 * 在某些实现中，中断锁获取的能力可能是不可能的，并且如果可能的话可能是昂贵的操作。程序员应意识到可能是这种情况。在这种情况下，实现应记录在案。
 * 与正常方法返回相比，实现可能更喜欢对中断做出响应。
 * 锁实现可能能够检测到锁的错误使用，例如可能导致死锁的调用，并且在这种情况下可能引发（未经检查的）异常。该Lock实现必须记录情况和异常类型。
 */
public class M0ReentrantInterruptibly {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();


        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("1- interrupted!");
            } finally {
                lock.unlock();
            }
        }, "first");
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                lock.lockInterruptibly();//  拿不到锁，不会进入wait，自动退出
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("2- interrupted!");
            } finally {
                lock.unlock();
            }
        }, "second");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
