package com.first.abstractKeyWord.a;

/**
 * @author luoxiaoqing
 * @date 2018-01-15__14:46
 */
public abstract class MyAbstractClass implements MyInterface {

    private String  tempFiled;

    public MyAbstractClass(String tempFiled) {
        this.tempFiled = tempFiled;
    }

    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    public abstract void run();
}
