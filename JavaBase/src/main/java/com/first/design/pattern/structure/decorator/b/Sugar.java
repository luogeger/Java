package com.first.design.pattern.structure.decorator.b;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:53
 */
public class Sugar implements Drink {

    private Drink concreteDecorator;

    /**
     * 具体的装饰器, 就是当前类的实现类
     *
     * @param concreteDecorator
     */
    public Sugar(Drink concreteDecorator) {
        this.concreteDecorator = concreteDecorator;
    }


    @Override
    public double money() {
        return concreteDecorator.money() + 2.0;
    }

    @Override
    public String desc() {
        return concreteDecorator.desc() + " +糖";
    }
}
