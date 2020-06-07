package com.first.abstracts.a;

/**
 * @author luoxiaoqing
 * @date 2018-01-15__14:46
 */
public interface MyInterface {

    void eat();

    void fly();


    /**
     * 接口的抽象方法， 子类为抽象类，可以不用重写，但是孙子类必须重写
     */
    void dance();
}
