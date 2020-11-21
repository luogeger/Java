package com.first.design.pattern.structure.proxy.d;

public class TimeProxy implements Work {


    private Work work;

    public TimeProxy(Work work) {
        this.work = work;
    }

    @Override
    public void meeting(String name) {
        //  聚合了被代理类，在需要被增加的方法上写代码
        long start = System.currentTimeMillis();
        work.meeting(name);
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("开会用了【" + totalTime + "】毫秒");
    }

    @Override
    public boolean coding() {
        return false;
    }

    @Override
    public int wc() {
        return 0;
    }
}
