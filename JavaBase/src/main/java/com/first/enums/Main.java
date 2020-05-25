package com.first.enums;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author luoxiaoqing
 * @date 2020-05-22__10:54
 * @desc
 */
public class Main {

    @Test
    public void main1 () {
        StrategyEnum strategyEnum = StrategyEnum.getaa(1);

        System.out.println(strategyEnum.validation());
    }

    @Test
    public void main2() {
        int salary = StrategyEnum.BOSS.validation();
        System.out.println(salary);


        List<Integer> nums = Lists.newArrayList(1, 23, 5, 33, 7, 8);
        int[] arr = {1, 23, 5, 33, 7, 8};


        System.out.println(arr);
    }
}
