package com.first.design.pattern.create.factory.method;

/**
 * @author luoxiaoqing
 * @date 2020-02-19__17:34
 */
public class MouseFactory {

    /**
     * 根据类型生产对象
     *
     * @param mouse
     * @return
     */
    public MouseInterface getInstanceByType(String mouse) {
        switch (mouse) {
            case "hp":
                return new HPMouse();
            case "dell":
                return new DellMouse();
            case "mac":
                return new MacMouse();
        }
        return null;
    }

    /**
     * 根据类的名称生产对象
     *
     * @param className
     * @return
     */
    public MouseInterface getInstanceByClassName(String className) {
        try {
            MouseInterface instance = (MouseInterface) Class.forName(className).newInstance();
            return instance;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
