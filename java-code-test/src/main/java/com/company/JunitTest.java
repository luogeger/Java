package com.company;

import org.junit.Test;

public class JunitTest {

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        int x = 10;
        x += x= x-x;
        System.out.println(x);
    }


}
