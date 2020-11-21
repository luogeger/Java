package com.first.design.pattern.structure.proxy.c;

/**
 * 静态代理类实现
 *
 *
 * 1. 被代理类的部分方法，不需要增强
 * 2. 代理类种类很多（日志，时间，事务，权限）
 * 3. 交叉
 * 4.
 *
 *
 */
public class Main {

    public static void main(String[] args) {
       test1();
    }


    /**
     * 记录时间
     */
    private static void test1() {
        Personnel person = new Personnel();
        LogProxy logProxy = new LogProxy(person);
        TimeProxy timeProxy = new TimeProxy(logProxy);

        //  这些方法的执行，先经过TimeProxy, 再经过LogProxy，最后才执行personnel的。所以代理类的方法体内斗必须要执行一次被代理类的方法
        timeProxy.meeting("职员");
        timeProxy.coding("Python");
    }
}


