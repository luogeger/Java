package com.first.first;

public class Base {
    private String baseName = "base";

    public Base() {
        callName();
    }

    private void callName() {
        System.out.println(baseName);
    }
}
