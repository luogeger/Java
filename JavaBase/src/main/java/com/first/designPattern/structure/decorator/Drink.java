package com.first.designPattern.structure.decorator;

/**
 * 装饰者抽象组件
 *
 * @author luoxiaoqing
 * @date 2020-02-11__22:33
 */
public interface Drink {

    /**
     * 饮品的价格
      * @return
     */
    double money();

    /**
     * 饮品的描述
     * @return
     */
    String desc();

}
