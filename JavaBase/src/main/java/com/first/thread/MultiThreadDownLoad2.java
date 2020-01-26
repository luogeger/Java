package com.first.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 * @author luoxiaoqing
 * @date 2019-06-30    10:58
 * @desc 多线程任务切割demo
 */
public class MultiThreadDownLoad2 {
    /**
     * 同时下载的线程数
     */
    private int threadCount;
    /**
     * 服务器请求路径
     */
    private String sourcePath;
    /**
     * 本地路径
     */
    private String destinationPath;
    /**
     * 线程计数同步辅助
     */
    private CountDownLatch latch;

    public MultiThreadDownLoad2(int threadCount, String sourcePath, String destinationPath, CountDownLatch latch) {
        this.threadCount = threadCount;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.latch = latch;
    }

    public void executeDownLoad() {

        try {
            File files = new File(sourcePath);
            //服务器返回的数据的长度，实际上就是文件的长度,单位是字节
            int length = (int) files.length();
            System.out.println("文件总长度:" + length + "字节(B)");
            RandomAccessFile raf = new RandomAccessFile(destinationPath, "rwd");
            //指定创建的文件的长度
            raf.setLength(length);
            raf.close();
            //分割文件
            int blockSize = length / threadCount;
            for (int threadId = 1; threadId <= threadCount; threadId++) {
                //第一个线程下载的开始位置
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = startIndex + blockSize - 1;
                if (threadId == threadCount) {
                    //最后一个线程下载的长度稍微长一点
                    endIndex = length;
                }
                System.out.println("线程" + threadId + "下载:" + startIndex + "字节~" + endIndex + "字节");
                new DownLoadThread(threadId, startIndex, endIndex).start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 内部类用于实现下载
     */
    public class DownLoadThread extends Thread {
        /**
         * 线程ID
         */
        private int threadId;
        /**
         * 下载起始位置
         */
        private int startIndex;
        /**
         * 下载结束位置
         */
        private int endIndex;

        public DownLoadThread(int threadId, int startIndex, int endIndex) {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }


        @Override
        public void run() {

            try {
                System.out.println("线程" + threadId + "正在下载...");
                File files = new File(sourcePath);
                FileInputStream fis = new FileInputStream(files);
                fis.skip(startIndex);


                RandomAccessFile raf = new RandomAccessFile(destinationPath, "rwd");
                //随机写文件的时候从哪个位置开始写
                raf.seek(startIndex);//定位文件

                int len = 0;
                byte[] buffer = new byte[1024];

                //获取文件片段长度
                int segLength = endIndex-startIndex+1;


                while ((len = fis.read(buffer)) != -1) {
                    if(segLength>len){
                        segLength = segLength-len;
                        raf.write(buffer, 0, len);
                    }else{
                        raf.write(buffer,0,segLength);
                        break;
                    }

                }
                fis.close();
                raf.close();
                System.out.println("线程" + threadId + "下载完毕");
                //计数值减一
                latch.countDown();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }




    public static void main(String[] args) {
        int threadSize=4;
        String sourcePath = "D:\\新建文件夹\\小项目.rar";
        String destnationPath = "D:\\bbb.rar";
        CountDownLatch latch = new CountDownLatch(threadSize);
        MultiThreadDownLoad2 m = new MultiThreadDownLoad2(threadSize, sourcePath, destnationPath, latch);
        long startTime = System.currentTimeMillis();
        try {
            m.executeDownLoad();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("全部下载结束,共耗时" + (endTime - startTime) / 1000 + "s");

    }


}



//  控制台如下：
//  文件总长度:120586816字节(B)
//  线程1下载:0字节~30146703字节
//  线程2下载:30146704字节~60293407字节
//  线程1正在下载...
//  线程3下载:60293408字节~90440111字节
//  线程4下载:90440112字节~120586816字节
//  线程2正在下载...
//  线程4正在下载...
//  线程3正在下载...
//  线程1下载完毕
//  线程3下载完毕
//  线程2下载完毕
//  线程4下载完毕
//  全部下载结束,共耗时120s

