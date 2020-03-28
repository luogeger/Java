package com.first.design.pattern.create.factorySimple;

/**
 * @author luoxiaoqing
 * @date 2020-02-19__17:34
 */
public class MouseFactory {

    /**
     * 根据类型生产对象
     * @param mouse
     * @return
     */
    public MouseInterface getInstanceByType(String mouse) {
        if (mouse == "hp") {
            return new HPMouse();
        } else if (mouse == "dell") {
            return new DellMouse();
        } else if (mouse == "mac") {
            return new MacMouse();
        }
        return null;
    }

    /**
     * 根据类的名称生产对象
     * @param className
     * @return
     */
    public MouseInterface getInstanceByClassName(String className) {
        try {
            MouseInterface instance = (MouseInterface) Class.forName(className).newInstance();
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}
