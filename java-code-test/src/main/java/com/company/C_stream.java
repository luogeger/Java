package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class C_stream {
    @Test
    public void test1() {
//        Stream<String> flow = Stream.of("11", "22", "33", "44", "55");
//        flow.stream().map(Integer::parseInt).forEach(System.out::println);

//        String [] list = {"11", "22", "33", "44", "55"};// cannot resolve method 'stream()'
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "11", "22", "33", "44", "66");
        list.stream().map(Integer::parseInt).forEach(System.out::println);// 把String转换为Integer
    }
}
