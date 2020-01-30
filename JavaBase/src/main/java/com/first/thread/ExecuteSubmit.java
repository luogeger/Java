package com.first.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author luoxiaoqing
 * @date 2018-01-14__12:11
 */
public class ExecuteSubmit {

    @Test
    public void submitTest() throws ExecutionException, InterruptedException {

        // 创建线程池
        ExecutorService threadPool =Executors.newCachedThreadPool();

        /**
         *  利用submit方法提交任务，接收任务的返回结果
         *  submit调用的是Callable接口的方法，已经做了异常处理
         */
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(1000L * 10);
            return 2 * 5;
        });

        //  阻塞方法，直到任务有返回值后，才向下执行
        Integer num = future.get();

        System.out.println("执行结果：" + num);
    }



    @Test
    public void executeTest() throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool =Executors.newCachedThreadPool();

        /**
         * 利用execute方法提交任务，没有返回结果
         *      execute调用的是Runnable接口的方法
         */
        threadPool.execute(() -> {
            try {
                Thread.sleep(1000L * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer num = 2 * 5;
            System.out.println("执行结果：" + num);
        });



        Thread.sleep(1000L * 1000);
    }


    @Test
    public void test1 () {


    }

}
