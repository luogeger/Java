package com.first.seckill.seckilldemo.service;

import com.first.seckill.seckilldemo.entity.User;

/**
 * @author luoxiaoqing
 */
public interface UserService {

    User selectOneById(Integer id);

    boolean tx();
}
