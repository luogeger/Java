package com.first.design.pattern.behavior.strategy.b;

/**
 * 是否也可以实现策略接口，或者中间再加一层抽象类
 *
 * @author luoxiaoqing
 * @date 2018-01-11__14:02
 */
public class CompressContext extends AbstractCompressStrategy{

    private CompressStrategy compressStrategy;

    public CompressContext(CompressStrategy compressStrategy) {
        this.compressStrategy = compressStrategy;
    }


    @Override
    public boolean compress(String source, String to) {
        return compressStrategy.compress(source, to);
    }


    @Override
    public boolean uncompress(String source, String to) {
        return compressStrategy.uncompress(source, to);
    }


}
