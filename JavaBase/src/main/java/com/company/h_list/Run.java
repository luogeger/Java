package com.company.h_list;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luoxiaoqing
 * @date 2019-06-28    11:29
 * @desc
 */
public class Run {

    @Test
    public void test1 () {

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();

        Collections.addAll(a, 1, 3, 5);
        Collections.addAll(b, 2, 4, 6);

        c.addAll(a);
        c.addAll(b);
        System.out.println(c.toArray());

    }


    @Test
    public void test2 () {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        Collections.addAll(a, 1, 3, 5, 7, 9, 9);
        Collections.addAll(b, 2, 4, 6, 7, 7, 9);
        System.out.println(a);
        System.out.println(b);
        a.retainAll(b);
        b.retainAll(a);
        a = a.stream().distinct().collect(Collectors.toList());
        b = b.stream().distinct().collect(Collectors.toList());
        System.out.println(a);
        System.out.println(b);
    }
}
