package com.first.seckill.seckilldemo.controller;

import com.first.seckill.seckilldemo.entity.User;
import com.first.seckill.seckilldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoxiaoqing
 */
@RestController
@RequestMapping("/seckill")
public class SampleController {
    @Autowired
    UserService userService;

    @GetMapping("/thyme")
    public String thymeleaf (Model model) {
        model.addAttribute("name", "luoxiaoqing");

        return "hello";
    }

    @GetMapping("/db/get/{id}")
    public User doGet(@PathVariable("id") Integer id) {
        User user = userService.selectOneById(id);
        return user;
    }


}
