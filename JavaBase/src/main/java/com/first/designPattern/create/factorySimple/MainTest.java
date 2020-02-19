package com.first.designPattern.create.factorySimple;

import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-02-19__17:31
 */
public class MainTest {
    /**
     *
     */
    @Test
    public void main1 () {
        DellMouse dell = new DellMouse();
        dell.getMouse();
        HPMouse hp = new HPMouse();
        hp.getMouse();
    }


    /**
     * 创建工厂
     */
    @Test
    public void main2 () {
        MouseFactory factory = new MouseFactory();
        MouseInterface hp = factory.getInstanceByType("hp");
        hp.getMouse();
        MouseInterface mac = factory.getInstanceByType("mac");
        mac.getMouse();
    }


    /**
     * 抽象工厂
     *
     * 反射：根据类的名称和包的全路径实例化
     */
    @Test
    public void main3 () {
        MouseFactory factory = new MouseFactory();
        MouseInterface dell = factory.getInstanceByClassName("com.first.designPattern.create.factorySimple.DellMouse");
        dell.getMouse();
    }
}
