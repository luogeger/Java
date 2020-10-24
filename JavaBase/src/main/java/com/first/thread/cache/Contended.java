package com.first.thread.cache;


//import jdk.internal.vm.annotation.Contended;
/**
 *
 */
public class Contended {

    //  @Contended注解能保证被修饰的成员变量和其他的成员变量不存在同一个缓存行
//    @Contended
    volatile long x;

//    @Contended
    volatile long y;


    public static void main(String[] args) {

        Contended contended = new Contended();
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                contended.x = i;
            }
        });


        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                contended.y = i;
            }
        });
    }
}
