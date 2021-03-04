package com.first.design.pattern.behavior.template.method.loader;

public class Ext extends Loader {

    @Override
    protected void findClass() {
        System.out.println("Ext.findClass");
    }
}
