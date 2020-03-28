package com.first.design.pattern.behavior.strategy.a;

/**
 * @author luoxiaoqing
 * @date 2018-01-11__13:51
 */
public class ConcreteStrategy implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("ConcreteStrategy doSomething !");
    }
}
