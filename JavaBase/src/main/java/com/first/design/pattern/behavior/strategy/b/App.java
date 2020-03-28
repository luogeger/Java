package com.first.design.pattern.behavior.strategy.b;

/**
 * @author luoxiaoqing
 * @date 2018-01-11__14:01
 */
public class App {

    public static void main(String[] args) {
        System.out.println(CompressStrategy.VARIABLE);


        CompressContext context;
        System.out.println("========执行算法========");
        context = new CompressContext(new ZipStrategy());
        context.compress("c:\\file", "d:\\file.zip");
        context.uncompress("c:\\file.zip", "d:\\file");
        System.out.println("========切换算法========");
        context = new CompressContext(new GzipStrategy());
        context.compress("c:\\file", "d:\\file.gzip");
        context.uncompress("c:\\file.gzip", "d:\\file");


    }
}
