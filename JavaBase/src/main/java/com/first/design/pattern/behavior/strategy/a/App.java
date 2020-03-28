package com.first.design.pattern.behavior.strategy.a;

/**
 * @author luoxiaoqing
 * @date 2018-01-11__13:52
 */
public class App {

    public static void main(String[] args) {
        Strategy strategy = new ConcreteStrategy();
        Context context = new Context(strategy);
        context.doAnything(); // ConcreteStrategy doSomething !


    }
}
