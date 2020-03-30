package com.first.seckill.seckilldemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author luoxiaoqing
 */
@Component
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;


    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getPoolMaxTotal());
        config.setMaxIdle(redisConfig.getPoolMaxIdle());
        config.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

        JedisPool jedisPool = new JedisPool(
                config,
                redisConfig.getHost(),
                redisConfig.getPort(),
                redisConfig.getTimeout() * 1000
        );
        return jedisPool;
    }
}
