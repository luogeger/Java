package com.first.abstractKeyWord.b;

import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-02-06__03:13
 */
public class Main {

    @Test
    public void main1 () {
        SubClass sub = new SubClass("aaa");
        MyAbstract sub1 = new SubClass("aaa");
        MyInterface sub2 = new SubClass("aaa");
        SubInterfaceImpl sub3 = new SubInterfaceImpl();
        sub.sing();
        System.out.println(sub.getTempFiled());
        /**
         * 抽象类可以获取接口的变量,【实现类不能获取接口的变量】
         */
        System.out.println(MyAbstract.NAME);
        /**
         * 接口名直接调用
         */
        System.out.println(MyInterface.NAME);

    }
}
