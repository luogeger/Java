package com.first.instruction;

/**
 * 函数指针，
 * JVM没有纯粹的函数，
 * 内部类的匿名内部类，
 * 看似传递是一个方法（回调函数），实际上传递是一个匿名内部类。
 *
 */
public class InvokeDynamic {

    public static void main(String[] args) {
        InvokeDynamicService i1 = () -> {
            InvokeDynamicServiceImpl.impl();
        };
        InvokeDynamicService i2 = () -> InvokeDynamicServiceImpl.impl();
        InvokeDynamicService i3 = InvokeDynamicServiceImpl::impl;

        System.out.println(i1.getClass());
        System.out.println(i2.getClass());
        System.out.println(i3.getClass());
    }
}


