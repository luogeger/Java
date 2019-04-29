package com.company.b_USB;

public class Mouse implements USB {
    @Override
    public void checkStart() {
        System.out.println("开始检测鼠标");
    }

    @Override
    public void checkClose() {
        System.out.println("鼠标检测完毕");
    }

    public void check() {
        System.out.println("正在检测鼠标...");
    }
}
