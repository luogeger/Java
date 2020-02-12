package com.first.service.impl;

import com.first.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author luoxiaoqing
 * @date 2018-01-26__14:38
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String get(String  num) {
        return num;
    }

    @Override
    public String add(String num) {
        return num;
    }

    @Override
    public String update(String num) {
        return num;
    }

    @Override
    public String delete(String num) {
        return num;
    }


    public void getUser() {

    }


    public void addUser(int a , int b) {

    }



}
