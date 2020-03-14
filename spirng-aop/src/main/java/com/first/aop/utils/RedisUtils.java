package com.first.aop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author luoxiaoqing
 * @date 2020-02-22__16:20
 */
@Component
public class RedisUtils {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public boolean getKey(String key) {

        return true;
    }

    public boolean setKey(String s, int i, long millisecond) {
        //redisTemplate.opsForValue().set();
        return true;
    }

    public boolean setKey(String url, String params, long millisecond) {

        //  key是url，val是参数，判断key是否存在，val是否相同
//        redisTemplate.opsForValue().set(url, params, millisecond, TimeUnit.MILLISECONDS);
        return true;
    }


}
