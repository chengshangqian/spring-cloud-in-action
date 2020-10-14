package com.fandou.coffee.learning.springcloud.security.support;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * id生成器
 */
public class LongIdGenerator implements IdGenerator {

    private RedisTemplate redisTemplate;

    public LongIdGenerator(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long id(String key){
        return redisTemplate.opsForValue().increment(key);
    }
}
