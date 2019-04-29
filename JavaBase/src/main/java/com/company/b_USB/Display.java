package com.company.b_USB;

public class Display implements USB {
    @Override
    public void checkStart() {
        System.out.println("开始检测显示器");
    }

    @Override
    public void checkClose() {
        System.out.println("显示器检测完毕");
    }

    public void check () {
        System.out.println("正在检测显示器...");
    }
}
