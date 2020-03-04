package com.first.service;

/**
 * @author luoxiaoqing
 * @date 2020-02-12__16:50
 */
public class Decorator extends AbstractTestService {
    public Decorator(TestService service) {
        super(service);
    }

    @Override
    public String get(String num) {
        String s = super.get(num);
        return s + "+get";
    }

    @Override
    public String add(String num) {
        return super.add(num) + "+add";
    }
}
