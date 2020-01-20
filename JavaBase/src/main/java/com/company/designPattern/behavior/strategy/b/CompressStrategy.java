package com.company.designPattern.behavior.strategy.b;

/**
 * 策略接口： 抽象出行为
 *
 * @author luoxiaoqing
 * @date 2020-01-11__13:58
 */
public interface CompressStrategy {

    boolean compress(String source, String to);

    boolean uncompress(String source, String to);
}
