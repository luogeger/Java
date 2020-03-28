package com.first.design.pattern.create.factorySimple;

/**
 * @author luoxiaoqing
 * @date 2020-02-19__17:32
 */
public class DellMouse  implements MouseInterface {
    @Override
    public void getMouse() {
        System.out.println("dell mouse");
    }
}
