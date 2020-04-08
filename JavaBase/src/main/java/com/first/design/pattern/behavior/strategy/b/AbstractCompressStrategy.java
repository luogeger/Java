package com.first.design.pattern.behavior.strategy.b;

/**
 * @author luoxiaoqing
 */
public abstract class AbstractCompressStrategy implements CompressStrategy{
    @Override
    public boolean compress(String source, String to) {
        return false;
    }

    @Override
    public boolean uncompress(String source, String to) {
        return false;
    }
}
