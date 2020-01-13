package com.company.e_stream;

/**
 * @author luoxiaoqing
 * @date 2020-01-13__16:53
 */
@FunctionalInterface
public interface FunctionalInterfaceDemo {

    /**
     * 只能有一个抽象方法
     */
    void method();


    /**
     * 可以有默认方法, 只能通过实例调用
     * @return
     */
    default Integer sum(){
        return null;
    }


    /**
     * 接口可以有静态方法
     */
    static void test(){

    };


}
