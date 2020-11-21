package com.first.design.pattern.structure.decorator.b;

/**
 * 该类是装饰器，管理具体的装饰类。
 * 装饰器的作用就是把具体的装饰类装饰到需要被装饰的类上。
 * 然后再返回被装饰之后的对象。
 * 装饰类必须满足的3个条件
 * 1， 抽象类
 * 2， 实现抽象方法
 * 3， 持有抽象接口的引用
 *
 */
public abstract class AbstractDecorator implements Drink {

    private Drink drink;

    public AbstractDecorator(Drink concreteDecorator) {
        this.drink = concreteDecorator;
    }

    @Override
    public double money() {
        return drink.money();
    }

    @Override
    public String desc() {
        return drink.desc();
    }
}
