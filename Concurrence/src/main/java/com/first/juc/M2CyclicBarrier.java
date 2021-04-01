package com.first.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程计数器: 人满发车
 * <p>
 * 一种同步帮助，它允许一组线程全部互相等待以到达一个公共的障碍点。 CyclicBarriers在涉及固定大小的线程方的程序中很有用，该线程方有时必须互相等待。 该屏障称为循环屏障，因为它可以在释放等待线程之后重新使用。
 * CyclicBarrier支持可选的Runnable命令，该命令在障碍中的最后一个线程到达之后但在释放任何线程之前，每个障碍点运行一次。 此屏障操作对于在任何一方继续之前更新共享状态很有用。
 * 用法示例：这是在并行分解设计中使用屏障的示例：
 * <p>
 * 在这里，每个工作线程处理矩阵的一行，然后在屏障处等待，直到所有行都已处理完毕。 处理完所有行后，将执行提供的Runnable屏障操作并将其合并。 如果合并确定已找到解决方案，则done（）将返回true，并且每个工作程序都将终止。
 * 如果屏障操作不依赖于执行时暂停的各方，则该方中的任何线程都可以在释放该操作时执行该操作。 为方便起见，每次调用wait都会返回该线程在屏障处的到达索引。 然后，您可以选择应该执行屏障操作的线程，例如：
 * <p>
 * CyclicBarrier对失败的同步尝试使用全有或无损坏模型：如果线程由于中断，失败或超时而过早离开屏障点，则所有其他在该屏障点等待的线程也将通过BrokenBarrierException（或InterruptedException）异常离开。 如果它们也大约同时被打断了）。
 * 内存一致性影响：线程中的操作在调用await（）之前发生在作为屏障操作一部分的操作之前，而继而又在其他线程中从相应await（）成功返回之后的操作发生在操作之前。
 */
public class M2CyclicBarrier {

    private static AtomicInteger count = new AtomicInteger(0);


    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);

        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("--- 满人"));

        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });*/

        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                try {
                    count.incrementAndGet();
                    System.out.println(count);
                    barrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
