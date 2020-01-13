package com.company.designPattern.behavior.strategy.c;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__14:26
 */
@FunctionalInterface
public interface ValidationStrategy {

    boolean validate(String string);
}