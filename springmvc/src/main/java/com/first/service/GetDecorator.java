package com.first.service;

/**
 * @author luoxiaoqing
 * @date 2020-02-12__16:50
 */
public class GetDecorator extends AbstractTestService {
    public GetDecorator(TestService service) {
        super(service);
    }

    @Override
    public String get(String num) {
        String s = super.get(num);
        return s + "+get";
    }
}
