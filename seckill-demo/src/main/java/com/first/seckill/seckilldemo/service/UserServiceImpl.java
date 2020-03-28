package com.first.seckill.seckilldemo.service;

import com.first.seckill.seckilldemo.entity.User;
import com.first.seckill.seckilldemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoxiaoqing
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectOneById(Integer id) {
        User user = userMapper.selectOneById(id);
        return user;
    }
}
