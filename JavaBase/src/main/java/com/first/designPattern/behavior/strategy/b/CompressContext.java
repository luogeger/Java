package com.first.designPattern.behavior.strategy.b;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__14:02
 */
public class CompressContext {

    private CompressStrategy compressStrategy;

    public CompressContext(CompressStrategy compressStrategy) {
        this.compressStrategy = compressStrategy;
    }


    public boolean compress(String source, String to) {
        return compressStrategy.compress(source, to);
    }

    public boolean uncompress(String source, String to) {
        return compressStrategy.uncompress(source, to);
    }


}
