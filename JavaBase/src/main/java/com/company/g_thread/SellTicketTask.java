package com.company.g_thread;

public class SellTicketTask implements Runnable {
    private int tickets = 100;
    private Object lock = new Object();// 同步代码块的锁是任意对象

    private static int num = 100;
    @Override
    public void run() {
        while (true) {
//            notLock();

            objectLock();

            //thisLock();

//            staticLock();
        }

    }



    /**
     * 静态同步方法：锁是当前类的字节码对象
     * 票的数量 需要用static修饰
     */
    private static synchronized void staticLock() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() +"  staticLock\t"+ num);
            num--;
        }
    }

    /**
     * 非静态同步方法：锁是this
     */
    private synchronized void thisLock() {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() +"  thisLock\t"+ tickets);
            tickets--;
        }
    }


    /**
     * 同步代码块：锁是任意对象
     */
    private void objectLock() {
        synchronized (lock) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() +"  objectLock\t"+ tickets);
                tickets--;
            }
        }
    }

    /**
     * 无锁：出现跳票现象
     */
    private void notLock() {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() +"  notLock\t"+ tickets);
            tickets--;
        }
    }


}
