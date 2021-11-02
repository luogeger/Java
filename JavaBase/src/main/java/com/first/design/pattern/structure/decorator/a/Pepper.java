package com.first.design.pattern.structure.decorator.a;

public class Pepper extends DefinedDecorator {
    public Pepper(Drink concreteDecorator) {
        super(concreteDecorator);
    }

    @Override
    public double money() {
        return super.money() + 1.3;
    }
}
