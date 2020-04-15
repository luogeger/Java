package com.first.constructor;

/**
 * @author luoxiaoqing
 */
public class Son extends Father {

    /**
     * 如果父类不提供无参构造器，必须调用父类的其他有参构造器
     * <p>There is no default constructor available in 'com.first.constructor.Father'</p>
     */
    public Son() {
        super("1");
        System.out.println("son");
    }


}
