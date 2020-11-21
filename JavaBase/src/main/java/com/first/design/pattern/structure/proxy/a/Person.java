package com.first.design.pattern.structure.proxy.a;

import java.util.Random;

/**
 *
 */
public class Person implements Work{
    @Override
    public void doSomething() {
        System.out.println("上班摸鱼了...");

        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
