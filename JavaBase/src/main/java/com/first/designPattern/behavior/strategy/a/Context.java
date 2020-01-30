package com.first.designPattern.behavior.strategy.a;

/**
 * @author luoxiaoqing
 * @date 2018-01-11__13:52
 */
public class Context {

    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doAnything() {
        this.strategy.doSomething();
    }
}
