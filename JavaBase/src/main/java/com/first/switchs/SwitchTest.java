package com.first.switchs;

import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-02-05__23:20
 */
public class SwitchTest {

    @Test
    public void main1() {
        String param = null;
        //  空指针异常
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }
}
