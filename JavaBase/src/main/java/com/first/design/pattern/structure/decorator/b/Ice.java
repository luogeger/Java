package com.first.design.pattern.structure.decorator.b;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:55
 */
public class Ice implements Drink{
    private Drink concreteDecorator;

    /**
     * 具体的装饰器, 就是当前类的实现类
     *
     * @param concreteDecorator
     */
    public Ice(Drink concreteDecorator) {
        this.concreteDecorator = concreteDecorator;
    }

    @Override
    public double money() {
        return concreteDecorator.money() + 1;
    }

    @Override
    public String desc() {
        return concreteDecorator.desc() + " +冰";
    }
}
