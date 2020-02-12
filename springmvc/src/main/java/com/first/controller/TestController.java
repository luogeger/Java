package com.first.controller;


import com.first.service.GetDecorator;
import com.first.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoxiaoqing
 * @date 2018-01-26__13:27
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestService testService;

    @GetMapping("/user")
    public String getUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attr.getRequest();
        String authorization = request.getHeader("Authorization");
        logger.info("token: {}",authorization);

        return "test user";
    }

    @GetMapping("/get/{num}")
    public String get(@PathVariable String num) {
        GetDecorator getDecorator = new GetDecorator(testService);
        String s = getDecorator.get(num);
        return s;
    }

    @GetMapping("/add/{num}")
    public String add(@PathVariable String num) {
        String s = testService.add(num);
        return s;
    }

    @GetMapping("/update/{num}")
    public String update(@PathVariable String num) {
        String s = testService.update(num);
        return s;
    }

    @GetMapping("/delete/{num}")
    public String delete(@PathVariable String num) {
        String s = testService.delete(num);
        return s;
    }








}
