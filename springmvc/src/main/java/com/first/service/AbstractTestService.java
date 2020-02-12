package com.first.service;

/**
 * @author luoxiaoqing
 * @date 2020-02-12__16:48
 */
public class AbstractTestService implements TestService {
    private TestService service;

    public AbstractTestService(TestService service) {
        this.service = service;
    }

    @Override
    public String get(String num) {
        return null;
    }

    @Override
    public String add(String num) {
        return null;
    }

    @Override
    public String update(String num) {
        return null;
    }

    @Override
    public String delete(String num) {
        return null;
    }
}
