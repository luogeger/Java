package com.first.design.pattern.behavior.proxy.c;

public class LogProxy implements Work {

    private Work work;

    public LogProxy(Work work) {
        this.work = work;
    }

    @Override
    public void meeting(String name) {
        System.out.println("meeting前记录日志");
        work.meeting(name);
        System.out.println("meeting后记录日志");

    }

    @Override
    public boolean coding(String code) {
        System.out.println("coding前记录日志");
        work.coding(code);
        System.out.println("coding后记录日志");
        return false;
    }

    @Override
    public int wc() {
        return 0;
    }
}
