package com.first.design.pattern.structure.proxy.c;

import java.util.Random;

public class Personnel implements Work {
    @Override
    public void meeting(String name) {
        System.out.println(name +"开会了...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean coding(String code) {
        System.out.println("personnel正在写 "+ code +" 代码");
        return false;
    }

    @Override
    public int wc() {
        return 0;
    }
}
