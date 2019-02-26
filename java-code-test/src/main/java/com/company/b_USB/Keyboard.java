package com.company.b_USB;

public class Keyboard implements USB {
    @Override
    public void checkStart() {
        System.out.println("开始检测键盘");
    }

    @Override
    public void checkClose() {
        System.out.println("键盘检测完毕");
    }

    public void check () {
        System.out.println("正在检测键盘...");
    }
}
