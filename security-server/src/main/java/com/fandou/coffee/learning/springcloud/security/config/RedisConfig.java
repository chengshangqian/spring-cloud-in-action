package com.fandou.coffee.learning.springcloud.security.config;

import com.fandou.coffee.learning.springcloud.security.support.LongIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public LongIdGenerator idGenerator(){
        return new LongIdGenerator(redisTemplate);
    }
}
