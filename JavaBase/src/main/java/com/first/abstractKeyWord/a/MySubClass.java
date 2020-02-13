package com.first.abstractKeyWord.a;

/**
 * 继承抽象类，
 * 1， 可以不重写抽象类的方法，
 * 2， 必须重写构造器
 *
 * @author luoxiaoqing
 * @date 2018-01-15__14:47
 */
public class MySubClass extends MyAbstractClass {


    /**
     * 子类必须实现抽象类的构造器
     * @param tempFiled
     */
    public MySubClass(String tempFiled) {
        super(tempFiled);
    }

    @Override
    public void run() {

    }

    @Override
    public void dance() {

    }

}
