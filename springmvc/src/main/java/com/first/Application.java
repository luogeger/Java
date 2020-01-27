package com.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luoxiaoqing
 * @date 2020-01-26__13:27
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println(123);
        SpringApplication.run(Application.class, args);
    }
}
