package com.first.thread.pool;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                10,
                20,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new DefaultThreadFactory("测试卖票"));


        threadPool.execute(new Ticket());
        threadPool.execute(new Ticket());
        threadPool.execute(new Ticket());
        threadPool.shutdown();

    }


}

/**
 * The default thread factory
 */
class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "【" + name + "-线程池编号:" +
                poolNumber.getAndIncrement() +
                "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement() + "】 ",
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}

/**
 *
 */
class Ticket implements Runnable {
    // 为了保持票数的一致，票数要静态
    static int tick = 20;
    // 锁
    static final Object LOCK = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (tick > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + tick + "张票，" + "剩余" + (tick-1) + "张票");
                    tick--;
                } else {
                    System.out.println(Thread.currentThread().getName() + "余票不足,停止售票!");
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
