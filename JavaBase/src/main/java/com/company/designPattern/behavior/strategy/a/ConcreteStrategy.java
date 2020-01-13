package com.company.designPattern.behavior.strategy.a;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__13:51
 */
public class ConcreteStrategy implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("ConcreteStrategy doSomething !");
    }
}