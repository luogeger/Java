package com.company;

import org.junit.Test;

import java.util.stream.Stream;

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

    @Test
    public void test3() {
        Integer [] num = {1,2,3,4,5,6};

        Integer[] evens =
                Stream.of(num).filter(n -> n%2 == 0).toArray(Integer[]::new);

        System.out.println(num);
        System.out.println(evens);


        Stream.of(evens).forEach(item -> item++);
        System.out.println(num);
    }


}
