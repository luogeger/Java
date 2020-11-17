package com.first.design.pattern.behavior.proxy.b;

public class Proxy implements Work {

    private Work work;

    public Proxy(Work work) {
        this.work = work;
    }

    @Override
    public void doSomething() {
        System.out.println("doSomething前");
        long start = System.currentTimeMillis();
        work.doSomething();
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("doSomething后");
        System.out.println("开会用了【" + totalTime + "】毫秒");
    }
}
