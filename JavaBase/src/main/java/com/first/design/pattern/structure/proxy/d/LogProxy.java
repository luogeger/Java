package com.first.design.pattern.structure.proxy.d;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 现在的代理类就不在实现Work接口了
 *
 *
 */
public class LogProxy  implements InvocationHandler {

    private Work work;

    public LogProxy(Work work) {
        this.work = work;
    }


    /**
     * 现在就是传递方法名，参数
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " 前记录日志，");
        Object o = method.invoke(work, args);
        System.out.println(method.getName() + " 后记录日志");
        return o;
    }


    /**
     * 这是之前在方法前面写逻辑
     * @param name
     */
    public void meeting(String name) {
        System.out.println("meeting前记录日志");
        work.meeting(name);
        System.out.println("meeting后记录日志");

    }

}
