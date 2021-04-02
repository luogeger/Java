package com.first.juc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  曾经的面试题：（淘宝？）
 *  实现一个容器，提供两个方法，add，size
 *  写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 *  分析下面这个程序，能完成这个功能吗？
 *
 *
 *
 *
 */
public class M7mst {

    //private volatile List lists = new ArrayList();// 即使加了volatile，也有可能读不到size==5; TimeUnit.SECONDS.sleep(1);
    private volatile List lists = Collections.synchronizedList(new LinkedList<>());

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        M7mst c = new M7mst();

        new Thread(() -> {
            for(int i=0; i<10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "t1").start();

        new Thread(() -> {
            while(true) {
                if(c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }

//    private volatile static List<Integer> list = new ArrayList<>();
//
//    public static void main(String[] args) {
//         new Thread(()-> {
//             while (true) {
//                 int size = list.size();
//                 if (size == 5) {
//                     System.out.println("到5");
//                     break;
//                 }
//             }
//         }, "monitor").start();
//
//
//         new Thread(()-> {
//             while (true) {
//                 try {
//                     TimeUnit.SECONDS.sleep(1);
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 }
//                 list.add(1);
//
//                 int size = list.size();
//                 if (size == 20) {
//                     System.out.println("满20了");
//                     break;
//                 }
//             }
//         }, "add").start();
//    }
}
