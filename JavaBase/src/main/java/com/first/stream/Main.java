package com.first.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Stream 各种方法演示
 */
public class Main {

    /**
     * groupingBy
     */
    @Test
    public void main1() {
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(result);
    }

    /**
     * groupingBy
     */
    @Test
    public void main2() {
        /*Map<String, List<item>> countMap = recordItems.stream().collect(Collectors.groupingBy(o -> o.getProductType()));

        List<Record> records = new ArrayList<Record>;
        countMap.keySet().forEach(productType -> {
            Map<String, Long> countMap1 = countMap.get(productType).stream().collect(Collectors.groupingBy(o -> o.getCountry(), Collectors.counting()));

            countMap1(key).stream().forEach(country -> {
                Record record = new Record();
                record.set("device_type", productType);
                record.set("location", country;
                record.set("count", countMap1(key).intValue());
                records.add(record);
            });
        });*/
    }
}
