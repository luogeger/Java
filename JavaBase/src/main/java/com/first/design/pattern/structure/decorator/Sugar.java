package com.first.design.pattern.structure.decorator;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:53
 */
public class Sugar extends AbstractDecorator{
    /**
     * @param concreteDecorator 具体的装饰器, 就是当前类的实现类
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
