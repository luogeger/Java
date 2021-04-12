package com.first.d.juc;

import java.util.concurrent.Semaphore;

/**
 * 限流：同时最多允许多少个线程执行
 *
 * 计数信号量。 从概念上讲，信号量维护一组许可证。 如有必要，每个获取块都将阻止，直到获得许可为止，然后再获取它。
 * 每个版本都会添加一个许可证，从而有可能释放阻塞的获取者。 但是，没有使用实际的许可对象。 信号量只是保持可用数量的计数并采取相应的措施。
 * 与无法访问某些（物理或逻辑）资源相比，信号量通常用于限制线程数。 例如，这是一个使用信号量控制对项目池的访问的类：
 */
public class M5Semaphore {

    public static void main(String[] args) {
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2, true);
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
