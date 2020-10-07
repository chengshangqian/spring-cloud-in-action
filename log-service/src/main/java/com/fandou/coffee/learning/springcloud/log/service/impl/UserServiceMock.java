package com.fandou.coffee.learning.springcloud.log.service.impl;

import com.fandou.coffee.learning.springcloud.log.service.UserService;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 用户服务熔断实现类
 */
@Service
public class UserServiceMock implements UserService {

    @Override
    public HttpResult<User> getUser(String accessToken, String username) {
        System.out.println("用户服务熔断被开启...");
        return null;
    }
}
