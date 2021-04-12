package com.first.d.juc;

import java.util.concurrent.Exchanger;

/**
 * 只能两个线程交换数据，
 *
 * 线程可以配对并在配对中交换元素的同步点。
 * 每个线程在进入交换方法时都会显示一些对象，与伙伴线程进行匹配，并在返回时接收其伙伴的对象。
 * 可以将Exchanger视为SynchronousQueue的双向形式。 交换器可能在遗传算法和管道设计等应用中很有用。
 *
 * 示例用法：下面是使用交换器在线程之间交换缓冲区的类的重点，
 * 以便填充缓冲区的线程在需要时获得一个新清空的线程，
 * 将填充的线程交给清空缓冲区的线程。
 */
public class M6Exchanger {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t1").start();


        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t2").start();


    }


}
