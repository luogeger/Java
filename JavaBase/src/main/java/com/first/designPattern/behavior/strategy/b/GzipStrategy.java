package com.first.designPattern.behavior.strategy.b;

/**
 * @author luoxiaoqing
 * @date 2018-01-11__14:00
 */
public class GzipStrategy implements CompressStrategy {

    @Override
    public boolean compress(String source, String to) {
        System.out.println(source + " --> " + to + " GZIP压缩成功!");
        return true;
    }

    @Override
    public boolean uncompress(String source, String to) {
        System.out.println(source + " --> " + to + " GZIP解压缩成功!");
        return true;
    }
}
