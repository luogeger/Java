package com.first.design.pattern.behavior.template.method.loader;

public class App extends Loader {

    @Override
    protected void findClass() {
        System.out.println("App.findClass");
    }

    @Override
    protected void loadClass() {
        System.out.println("App.loadClass");
    }
}
