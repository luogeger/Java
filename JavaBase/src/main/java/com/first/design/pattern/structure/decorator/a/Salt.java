package com.first.design.pattern.structure.decorator.a;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:48
 */
public class Salt extends AbstractDecorator{

    /**
     * @param concreteDecorator 具体的装饰器, 就是当前类的实现类
     */
    public Salt(Drink concreteDecorator) {
        super(concreteDecorator);
    }


    @Override
    public double money() {
        return super.money() + 3.2;
    }

    @Override
    public String desc() {
        return super.desc() + " +盐";
    }
}
