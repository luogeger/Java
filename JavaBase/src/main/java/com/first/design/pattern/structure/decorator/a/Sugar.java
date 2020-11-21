package com.first.design.pattern.structure.decorator.a;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:53
 */
public class Sugar extends AbstractDecorator {
    /**
     * 具体的装饰器, 就是当前类的实现类
     *
     * @param concreteDecorator
     */
    public Sugar(Drink concreteDecorator) {
        super(concreteDecorator);
    }


    @Override
    public double money() {
        return super.money() + 2.0;
    }

    @Override
    public String desc() {
        return super.desc() + " +糖";
    }
}
