package com.fandou.coffee.learning.springcloud.log.service.impl;

import com.fandou.coffee.learning.springcloud.log.service.UserService;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 用户服务熔断实现类
 */
@Service
public class UserServiceMock implements UserService {
    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceMock.class);

    @Override
    public HttpResult<User> getUser(String accessToken, String username) {

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("用户服务熔断被开启 => {accessToken -> {},username -> {}}",accessToken,username);
        }

        return null;
    }
}
