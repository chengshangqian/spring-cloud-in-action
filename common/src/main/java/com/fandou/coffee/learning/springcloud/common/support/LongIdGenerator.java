package com.fandou.coffee.learning.springcloud.common.support;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * id生成器
 */
public class LongIdGenerator implements IdGenerator {

    private final RedisTemplate redisTemplate;

    public LongIdGenerator(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long id(String key){
        return redisTemplate.opsForValue().increment(key);
    }
}
