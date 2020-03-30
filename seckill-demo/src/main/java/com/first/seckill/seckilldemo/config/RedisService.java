package com.first.seckill.seckilldemo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 提供Redis服务
 *
 * @author luoxiaoqing
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;


    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String val = jedis.get(key);
            T t = stringToBean(val, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(String key, T t) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String val = beanToString(t);
            if (val == null || val.length() == 0) {
                return false;
            } else {
                jedis.set(key, val);
                return true;
            }
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 任意类型转换成字符串
     *
     * @param t
     * @param <T>
     * @return
     */
    private <T> String beanToString(T t) {
        // 假如类型是Integer
        if (t == null) {
            return null;
        }
        // 再判断
        Class<?> clazz = t.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + t;
        } else if (clazz == String.class) {
            return (String) t;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + t;
        } else {
            return JSON.toJSONString(t);
        }
    }


    /**
     * 字符串 --> json --> Class
     * @param val
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T stringToBean(String val, Class<T> clazz) {
        if (val == null || val.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(val);
        } else if (clazz ==String.class) {
            return (T) val;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(val);
        } else {
            JSONObject json = JSON.parseObject(val);
            return JSON.toJavaObject(json, clazz);
        }
    }


    /**
     * 返回到连接池
     *
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


}
