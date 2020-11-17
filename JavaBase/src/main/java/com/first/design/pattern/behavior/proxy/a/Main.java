package com.first.design.pattern.behavior.proxy.a;

/**
 * 记录一个方法执行的时间： 手动在方法前后写代码
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
        long start = System.currentTimeMillis();
        person.doSomething();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
