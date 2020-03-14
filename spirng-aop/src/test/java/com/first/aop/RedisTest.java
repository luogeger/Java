package com.first.aop;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author luoxiaoqing
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    /**
     * 会把key和val序列化成二进制
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * String
     * @throws Exception
     */
    @Test
    public void main1() throws Exception {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        String aaa = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);

        // 如果key存在就返回false
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent("aaa", "setIfAbsent");
        System.out.println(aBoolean);
    }

    /**
     * 设置时间
     */
    @Test
    public void main2 () {
        stringRedisTemplate.opsForValue().set("name", "hello world", 30, TimeUnit.SECONDS);
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }


    /**
     * Map
     */
    @Test
    public void main3 () {
        RedisUser user1 = new RedisUser("lucy", 20);
        RedisUser user2 = new RedisUser("lili", 19);
        RedisUser user3 = new RedisUser("lilei", 21);

        //  存在user这个key的话就是获取，没有就是新增
        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps("user");
        hashOps.put("u1", JSONObject.toJSONString(user1));
        hashOps.put("u2", JSONObject.toJSONString(user2));
        hashOps.put("u3", JSONObject.toJSONString(user3));

        //  获取所有的key
        Set<Object> keys = hashOps.keys();
        System.out.println(keys.toArray().toString());

        //  获取的是所有的数据
        Map<Object, Object> entries = hashOps.entries();
        System.out.println(entries.toString());

    }



}














class RedisUser implements Serializable {
    private static final long serialVersionUID = -1L;
    private String username;
    private Integer age;
    public RedisUser(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}