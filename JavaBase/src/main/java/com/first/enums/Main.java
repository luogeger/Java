package com.first.enums;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * 测试类
 *
 * @author luoxiaoqing
 */
public class Main {

    @Test
    public void main1() {
        int salary = StrategyEnum.BOSS.getSalary();
        System.out.println(salary);


        List<Integer> nums = Lists.newArrayList(1, 23, 5, 33, 7, 8);
        int[] arr = {1, 23, 5, 33, 7, 8};


        System.out.println(arr);
    }
}
