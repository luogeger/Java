package com.first.aop.controller;

import com.first.aop.aop.NoRepeatSubmit;
import com.first.aop.entity.User;
import com.first.aop.utils.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/test")
public class TestController {

    @NoRepeatSubmit
    @RequestMapping(value = "/repeat/repeat", method = RequestMethod.POST)
    public Response test (@RequestBody User user) {
        return new Response(user);
    }


    @GetMapping("/one")
    public String test1 () {
        System.out.println("111");
        return "one";
    }



    @GetMapping("/two")
    public String test2() {
        return "two";
    }


}