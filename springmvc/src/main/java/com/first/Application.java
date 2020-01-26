package com.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoxiaoqing
 * @date 2020-01-26__13:27
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        System.out.println(123);
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("abc")
    public String abc() {
        return "abc";
    }

}
