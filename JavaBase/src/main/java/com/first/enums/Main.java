package com.first.enums;

import org.junit.Test;

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
}
