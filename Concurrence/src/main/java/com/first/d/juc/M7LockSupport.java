package com.first.d.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 *
 * 用于创建锁和其他同步类的基本线程阻塞原语。
 * 此类与使用它的每个线程关联一个许可（就Semaphore类而言）。
 * 如果有许可证，将立即返回停车请求，并在此过程中消耗掉它；否则可能会阻塞。
 * 取消停车的调用将使许可证可用（如果尚不可用）。 （不过与信号量不同，许可证不会累积。最多只能有一个。）
 * 方法Park和UnPark提供了一种有效的阻塞和解除阻塞线程的方法，
 * 这些线程不会遇到导致已弃用的方法Thread.suspend和Thread.resume无法用于以下目的的问题：
 * 一个线程在调用Park和试图取消其驻留的线程之间进行竞争根据许可，将保留生命。
 * 此外，如果调用者的线程被中断，并且支持超时版本，则驻留将返回。
 * park方法也可能由于“无故”而在其他任何时间返回，因此通常必须在循环中调用该循环，该循环在返回时会重新检查条件。
 * 从这个意义上讲，停车是对“繁忙等待”的一种优化，它不会浪费太多的时间，而必须与取消停车配对才能有效。
 * 停放的三种形式也都支持阻塞对象参数。线程被阻塞时会记录该对象，以允许监视和诊断工具确定线程被阻塞的原因。
 * （此类工具可以使用方法getBlocker（Thread）访问阻止程序。）
 * 强烈建议使用这些形式，而不使用没有此参数的原始形式。在锁实现中提供作为阻止程序的正常参数是这样的。
 * 这些方法旨在用作创建更高级别的同步实用程序的工具，而对于大多数并发控制应用程序本身并不有用。
 * 停放方法仅设计用于以下形式的结构：
 */
public class M7LockSupport {

    public static void main(String[] args) {
        Thread first = new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);

                if (i==5) {
                    //  停下来
                    LockSupport.park();
                    //LockSupport.park();// 提前unpark(), 仍然可以阻塞
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "first");

        first.start();

        System.out.println("停车前");
        LockSupport.unpark(first);
        //  停下来，再主线程继续
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("继续开车...");
        LockSupport.unpark(first);
    }

}
