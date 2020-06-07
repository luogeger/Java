package com.first.abstracts.a;

/**
 * @author luoxiaoqing
 * @date 2018-01-15__14:46
 */
public abstract class MyAbstractClass implements MyInterface {

    protected String  tempFiled;

    public MyAbstractClass(String tempFiled) {
        this.tempFiled = tempFiled;
    }

    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    abstract void run();
}
