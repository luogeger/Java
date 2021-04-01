package com.first.juc;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 可重用的同步屏障，其功能类似于CyclicBarrier和CountDownLatch，但支持更灵活的用法。
 * 登记。与其他障碍不同，注册在移相器上进行同步的参与方数量可能会随时间变化。
 * 可以随时注册任务（使用方法register，bulkRegister或建立初始方数的构造函数形式），
 * 也可以选择在任何到达时注销（使用ArrivalAndDeregister）。
 * 与大多数基本同步结构一样，注册和注销会只影响内部计数。
 * 它们不会建立任何进一步的内部簿记，因此任务无法查询它们是否已注册。 （但是，您可以通过将此类归为一类来介绍此类记帐。）
 * 同步。像CyclicBarrier一样，移相器可能会反复等待。方法到达和等待Advance具有类似于CyclicBarrier.await的作用。
 * 移相器的每一代都有一个关联的相数。阶段号从零开始，并在所有各方到达移相器时前进，在达到Integer.MAX_VALUE后回绕为零。
 * 通过使用任何注册方可以调用的两种方法，使用阶段编号可以独立控制到达移相器时和等待其他人时的动作：
 */
public class M3Phaser {

    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        phaser.bulkRegister(5);

        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(() -> {

                Person p = new Person("person " + nameIndex);
                p.arrive();
                phaser.arriveAndAwaitAdvance();

                p.eat();
                phaser.arriveAndAwaitAdvance();

                p.leave();
                phaser.arriveAndAwaitAdvance();
            }).start();
        }

    }


    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！");
                    return false;
                case 1:
                    System.out.println("所有人吃完了！");
                    return false;
                case 2:
                    System.out.println("所有人离开了！");
                    System.out.println("婚礼结束！");
                    return true;
                default:
                    return true;
            }
        }
    }


    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
        }

    }
}
