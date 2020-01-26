package com.first.USB;

public class Run {
    public static void main(String[] args) {
        Computer computer   = new Computer();
        USB mouse           = new Mouse();
        USB keyboard        = new Keyboard();
        USB display         = new Display();

        computer.start();
        computer.checking(mouse);
        computer.checking(keyboard);
        computer.checking(display);
        computer.close();
    }
}
