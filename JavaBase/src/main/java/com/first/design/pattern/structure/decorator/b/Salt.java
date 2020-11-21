package com.first.design.pattern.structure.decorator.b;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:48
 */
public class Salt implements Drink{

    private Drink concreteDecorator;

    /**
     * 具体的装饰器, 就是当前类的实现类
     *
     * @param concreteDecorator
     */
    public Salt(Drink concreteDecorator) {
        this.concreteDecorator = concreteDecorator;
    }


    @Override
    public double money() {
        return concreteDecorator.money() + 3.2;
    }

    @Override
    public String desc() {
        return concreteDecorator.desc() + " +盐";
    }
}
