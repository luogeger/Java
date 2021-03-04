package com.first.design.pattern.behavior.template.method.loader;

public abstract class Loader {

    protected void findClass () {
        System.out.println("Loader.findClass");
    }

    protected void loadClass () {
        System.out.println("Loader.loadClass");
        findClass();
    }
}
