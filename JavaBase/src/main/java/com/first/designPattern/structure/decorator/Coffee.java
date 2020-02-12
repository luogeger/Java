package com.first.designPattern.structure.decorator;

/**
 *
 * 被装饰者
 *
 * @author luoxiaoqing
 * @date 2020-02-11__23:07
 */
public class Coffee implements Drink {
    @Override
    public double money() {
        return 10;
    }

    @Override
    public String desc() {
        return "纯咖啡";
    }
}
