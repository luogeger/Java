package com.first.designPattern.structure.decorator;

/**
 * 该类是装饰器，管理具体的装饰类
 * 装饰类必须满足的3个条件
 * 1， 抽象类
 * 2， 实现抽象接口
 * 3， 持有抽象接口的引用
 * @author luoxiaoqing
 * @date 2020-02-11__22:37
 */
public abstract class AbstractDecorator implements Drink {

    private Drink drink;


    /**
     * @param concreteDecorator 具体的装饰器, 就是当前类的实现类
     */
    public AbstractDecorator(Drink concreteDecorator) {
        this.drink = concreteDecorator;
    }

    /**
     * 装饰器的作用就是把具体的装饰类装饰到需要被装饰的类上，
     * 然后，返回被装饰之后的对象
     */
    @Override
    public double money() {
        return drink.money();
    }

    @Override
    public String desc() {
        return drink.desc();
    }
}
