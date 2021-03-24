package com.first.gc;

import java.util.LinkedList;
import java.util.List;

/**
 * 堆溢出
 */
public class OOM {

    public static void main(String[] args) {
        System.out.println("HelloGC!");
        List list = new LinkedList();
        for(;;) {
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }
}
