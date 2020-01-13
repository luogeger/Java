package com.company.e_stream;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author luoxiaoqing
 * @date 2020-01-13__14:14
 */
public class StreamDataConversion {

    @Test
    public void main() {

        Stream<String> fruit = Stream.of("apple", "banana", "orange");

        //  拼接字符串
//        String collect = fruit.collect(Collectors.joining());
//        System.out.println(collect);


        // List
//        List<String> collect1 = fruit.collect(Collectors.toList());
//        System.out.println(collect1);

        //  Map
        Map<Object, Object> collect2 = fruit.collect(Collectors.toMap(x ->
                        "key-" + x
                , y ->
                        "value-" + y
        ));

        System.out.println(collect2);

    }


}
