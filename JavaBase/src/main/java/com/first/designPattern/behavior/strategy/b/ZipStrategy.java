package com.first.designPattern.behavior.strategy.b;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__13:59
 */
public class ZipStrategy implements CompressStrategy {

    @Override
    public boolean compress(String source, String to) {
        System.out.println(source + " --> " + to + " ZIP压缩成功!");
        return true;
    }

    @Override
    public boolean uncompress(String source, String to) {
        System.out.println(source + " --> " + to + " ZIP解压缩成功!");
        return true;
    }
}
