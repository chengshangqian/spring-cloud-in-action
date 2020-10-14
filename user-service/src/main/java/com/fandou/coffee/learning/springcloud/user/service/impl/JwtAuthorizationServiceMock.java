package com.fandou.coffee.learning.springcloud.user.service.impl;

import com.fandou.coffee.learning.springcloud.common.model.AccessToken;
import com.fandou.coffee.learning.springcloud.user.service.JwtAuthorizationService;
import org.springframework.stereotype.Service;

/**
 * 熔断处理
 */
@Service
public class JwtAuthorizationServiceMock implements JwtAuthorizationService {

    @Override
    public AccessToken getJwtAccessToken(String clientDetailsAuthorization, String type, String username, String password) {
        return null;
    }
}
