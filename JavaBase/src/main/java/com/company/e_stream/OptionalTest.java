package com.company.e_stream;

import org.junit.Test;

public class OptionalTest {

    // of	        把指定的值封装为Optional对象，如果指定的值为null，则抛出NullPointerException
    // empty	    创建一个空的Optional对象
    // ofNullable	把指定的值封装为Optional对象，如果指定的值为null，则创建一个空的Optional对象
    // get	        如果创建的Optional中有值存在，则返回此值，否则抛出NoSuchElementException
    // orElse	    如果创建的Optional中有值存在，则返回此值，否则返回一个默认值
    // orElseGet	如果创建的Optional中有值存在，则返回此值，否则返回一个由Supplier接口生成的值
    // orElseThrow	如果创建的Optional中有值存在，则返回此值，否则抛出一个由指定的Supplier接口生成的异常
    // filter	    如果创建的Optional中的值满足filter中的条件，则返回包含该值的Optional对象，否则返回一个空的Optional对象
    // map	        如果创建的Optional中的值存在，对该值执行提供的Function函数调用
    // flagMap	    如果创建的Optional中的值存在，就对该值执行提供的Function函数调用，返回一个Optional类型的值，否则就返回一个空的Optional对象
    // isPresent	如果创建的Optional中的值存在，返回true，否则返回false
    // ifPresent	如果创建的Optional中的值存在，则执行该方法的调用，否则什么也不做

    @Test
    public void test1 () {

    }
}
