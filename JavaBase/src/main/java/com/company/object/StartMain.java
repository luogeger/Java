package com.company.object;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__17:33
 */
public class StartMain {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
    }

    public static void change(int[] arr) {
        arr[0] = 200;
    }

    @Test
    public void test1() {

        //定义字符串
        String s = "s";
        String s1 = "s1";

        //比较上述字符串是否相等
        boolean b1 = s.equals(s1);
        System.out.println(b1);

        //使用Objects工具类比较上述字符串
        boolean b2 = Objects.equals(s, s1);
        System.out.println(b2);
    }


    @Test
    public void test2() {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(15);
        nums.add(21);
        nums.add(19);
        nums.add(13);
        nums.add(15);
        nums.add(10);

        System.out.println(nums.toString());
        Collections.sort(nums, (a, b) -> {
            return a - b;
        });

        System.out.println(nums.toString());
    }
}
