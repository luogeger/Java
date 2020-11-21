package com.first.design.pattern.structure.decorator.a;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:35
 */
public class Milk implements Drink {

    @Override
    public double money() {
        //  牛奶的基本价格为5元
        return 5;
    }

    @Override
    public String desc() {
        return "牛奶";
    }
}
