package com.first.USB;

public class Computer {
    public void start() {
        System.out.println("电脑开机");
    }

    public void close() {
        System.out.println("电脑关机");
    }

    public void checking(USB usb) {
        usb.checkStart();
        //usb.check();// cannot resolve method 'check()'
        if (usb instanceof Mouse) ((Mouse) usb).check();
        if (usb instanceof Keyboard) ((Keyboard) usb).check();
        if (usb instanceof Display) ((Display) usb).check();

        usb.checkClose();

    }
}
