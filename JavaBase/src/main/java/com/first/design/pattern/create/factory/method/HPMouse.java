package com.first.design.pattern.create.factory.method;

/**
 * @author luoxiaoqing
 * @date 2020-02-19__17:32
 */
public class HPMouse implements MouseInterface {
    @Override
    public void getMouse() {
        System.out.println("hp mouse");
    }
}
