package com.first.constructor;

/**
 * @author luoxiaoqing
 */
public class Father {
    private String  id;

    /**
     * 一般父类具备有参构造器，建议也提供无参构造器
     */
    /*public Father() {
    }*/

    public Father(String id) {
        System.out.println("father");
        this.id = id;
    }
}
