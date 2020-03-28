package com.first.design.pattern.structure.decorator;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:35
 */
public class SoyaComponent implements Drink {

    @Override
    public double money() {
        //  纯豆浆的基本价格为5元
        return 5;
    }

    @Override
    public String desc() {
        return "这是纯豆浆";
    }
}
