package com.first.design.pattern.behavior.templateMethod.code;

/**
 * @author luoxiaoqing
 */
public class SmsCode extends AbstractTemplate {
    @Override
    protected void validate() {
        System.out.println("验证短信码");
    }


    @Override
    protected void generate() {
        System.out.println("发送短信码");
    }

    @Override
    protected boolean visitCustomersHook() {
        return false;
    }
}
