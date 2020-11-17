//package com.first.thread.fiber;
//
//import co.paralleluniverse.fibers.Fiber;
//import co.paralleluniverse.fibers.SuspendExecution;
//import co.paralleluniverse.strands.SuspendableRunnable;
//
//public class HelloFiber {
//
//    public static void main(String[] args) throws Exception {
//        long start = System.currentTimeMillis();
//        int size = 100_000;
//
//        //  任务
///*
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                calc();
//            }
//        };
//
//        //  1.线程版：并行化执行
//        Thread[] threadArr = new Thread[size];
//        //  创建
//        for (int i = 0; i < threadArr.length; i++) {
//            threadArr[i] = new Thread(r);
//        }
//        //  start
//        for (int i = 0; i < threadArr.length; i++) {
//            threadArr[i].start();
//        }
//        //  join
//        for (int i = 0; i < threadArr.length; i++) {
//            threadArr[i].join();
//        }
//*/
//
//
//        //  2.纤程版
///**/        Fiber<Void>[] FiberArr = new Fiber[size];
//        //  创建
//        for (int i = 0; i < FiberArr.length; i++) {
//            FiberArr[i] = new Fiber<Void>(new SuspendableRunnable() {
//                @Override
//                public void run() throws SuspendExecution, InterruptedException {
//                    calc();
//                }
//            });
//        }
//        //  start
//        for (int i = 0; i < FiberArr.length; i++) {
//            FiberArr[i].start();
//        }
//        //  join
//        for (int i = 0; i < FiberArr.length; i++) {
//            FiberArr[i].join();
//        }
//
//
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//    }
//    /**
//     *
//     */
//    static void calc() {
//        int result = 0;
//        for (int m = 0; m < 10000; m++) {
//            for (int i = 0; i < 200; i++) {
//                result += i;
//            }
//        }
//    }
//}
