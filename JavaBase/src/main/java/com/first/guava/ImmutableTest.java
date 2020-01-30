package com.first.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luoxiaoqing
 * @date 2018-01-13__19:42
 */
public class ImmutableTest {
    /**
     * 不可变集合的特点
     *  1）安全
     *  2）不存在竞态条件
     *  3）节省时间和空间
     *  4）可以作为常量来使用
     */

    public static void test(List<Integer> list) {
        list.remove(0);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> newList = Collections.unmodifiableList(list);
        test(newList);
        System.out.println(newList);
    }

    public void immutable() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        /**
         * 构造不可变集合对象三种方式
         */
        // 通过已经存在的集合创建
        ImmutableSet.copyOf(list);

        // 通过初始值，直接创建不可变集合
        ImmutableSet immutableSet =
                ImmutableSet.of(1, 2, 3);

        // 以builder方式创建
        ImmutableSet.builder()
                .add(1)
                .addAll(Sets.newHashSet(2, 3))
                .add(4)
                .build();

    }
}
