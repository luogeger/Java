package com.first.design.pattern.behavior.strategy.c;

/**
 * @author luoxiaoqing
 * @date 2018-01-11__14:28
 */
public class Context {

    private final ValidationStrategy validationStrategy;

    public Context(ValidationStrategy strategy) {
        validationStrategy = strategy;
    }


    public boolean execute (String str) {
        return validationStrategy.validate(str);
    }
}
