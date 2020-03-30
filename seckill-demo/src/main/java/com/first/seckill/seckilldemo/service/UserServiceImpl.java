package com.first.seckill.seckilldemo.service;

import com.first.seckill.seckilldemo.entity.User;
import com.first.seckill.seckilldemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public boolean tx() {
        User user2 = new User();
        user2.setId(2);
        user2.setName("2222");
        userMapper.insert(user2);

        User user1 = new User();
        user1.setId(1);
        user1.setName("111");
        userMapper.insert(user1);
        return true;
    }
}
