package com.company.designPattern.behavior.strategy.b;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__13:58
 */
public interface CompressStrategy {

    public boolean compress(String source, String to);

    public boolean uncompress(String source, String to);
}
