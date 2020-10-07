package com.fandou.coffee.learning.springcloud.blog.service.impl;

import com.fandou.coffee.learning.springcloud.blog.service.UserService;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import org.springframework.stereotype.Service;

/**
 * 用户服务熔断实现类
 */
@Service
public class UserServiceMock implements UserService {
    @Override
    public HttpResult<User> getByUsername(String accessToken, String username) {
        return null;
    }
}
