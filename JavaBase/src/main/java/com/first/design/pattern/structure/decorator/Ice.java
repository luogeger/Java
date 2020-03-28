package com.first.design.pattern.structure.decorator;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:55
 */
public class Ice extends AbstractDecorator {
    /**
     * @param concreteDecorator 具体的装饰器, 就是当前类的实现类
     */
    public Ice(Drink concreteDecorator) {
        super(concreteDecorator);
    }

    @Override
    public double money() {
        return super.money() + 1;
    }

    @Override
    public String desc() {
        return super.desc() + " +冰";
    }
}
