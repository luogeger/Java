package com.first.design.pattern.structure.proxy.d;


import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 * <p>
 * 相对于静态代理，
 *  1. 代理类还是要实现接口
 *  2. 方法执行的时候，虽然不需要把目标对象当做代理的构造器参数传递，但是多了Proxy.newProxyInstance()这个方法
 *  3.
 * <p>
 * 1. JDK的动态代理是用Reflect来是实现的
 * a. 必须面向接口
 * b. 生成的代理类是接口的实现类，所以返回的必须是接口类型。
 * <p>
 * 2. CGLIB的动态代理可以不用实现接口，生成的代理类是被代理类的子类
 * a. final类不可以被代理
 */
public class Main {

    public static void main(String[] args) {

        Personnel personnel = new Personnel();

        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        //  必须返回接口类型
        //  Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to Personnel
        Work personnelProxy = (Work) Proxy.newProxyInstance(
                Personnel.class.getClassLoader(),
                new Class[]{Work.class},
                new LogProxy(personnel));

        personnelProxy.meeting("代理职工 ");

        personnelProxy.coding();


    }

}


