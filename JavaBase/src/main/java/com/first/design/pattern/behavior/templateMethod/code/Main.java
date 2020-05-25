package com.first.design.pattern.behavior.templateMethod.code;

import org.junit.Test;

/**
 * @author luoxiaoqing
 */
public class Main {

    @Test
    public void main1 () {
        SmsCode smsCode = new SmsCode();
        smsCode.sendCodeTemplate();

    }
}
