package com.first.design.pattern.structure.decorator.a;


public class DefinedDecorator  extends AbstractDecorator {
    public DefinedDecorator(Drink concreteDecorator) {
        super(concreteDecorator);
    }

    @Override
    public double money() {
        return super.money();
    }

    @Override
    public String desc() {
        return super.desc();
    }
}
