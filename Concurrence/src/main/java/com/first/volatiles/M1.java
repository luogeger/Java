package com.first.volatiles;


import java.util.concurrent.TimeUnit;

/**
 * volatile保证了线程之间的可见性，注意：只是保证了引用本身的可见性（包括数组），不能保证内部字段的可见性
 * 多线程之间的共享变量被修改之后，其他线程能及时被通知到。
 * 原理是volatile修饰的这块内存区域加了内存屏障，要求被访问的时候，被修改之后，都必须到主存。
 */
public class M1 {

    private /*volatile*/ boolean running = true;

    private void method() {
        System.out.println("m start");
        //  读取running的时候，先把主内存的值，读取到自己的工作内存
        while(running) {
            // doSomething...
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        M1 t = new M1();

        new Thread(t::method, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  这里也是先把主内存的值，读取到自己的工作内存，修改之后再写回主内存，但是t线程并没有读取到。
        //  running不加volatile修饰，t线程就不会停下来
        t.running = false;
    }
}
