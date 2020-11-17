package com.first.design.pattern.behavior.proxy.b;

/**
 * 现在需求升级
 * 不仅仅是记录时间，还要打印日志
 *
 *
 * 1. 代理类岂不是要实现所有的方法，哪怕有些方法不需要记录时间和打印日志
 * 2. 而且每个方法前后增强的逻辑是一样。
 * 3. 如果要再加一个代理功能，逻辑代码都是重复的。
 */
public class Main {

    public static void main(String[] args) {
       test1();
    }


    /**
     * 记录时间
     */
    private static void test1() {
        Person person = new Person();
        Proxy proxy = new Proxy(person);
        proxy.doSomething();
    }
}
