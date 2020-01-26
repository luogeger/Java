package com.first.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoxiaoqing
 * @date 2020-01-26__13:27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    public String getUser() {
        return "test user";
    }
}
