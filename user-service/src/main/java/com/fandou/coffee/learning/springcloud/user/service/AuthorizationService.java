package com.fandou.coffee.learning.springcloud.user.service;

import com.fandou.coffee.learning.springcloud.common.model.AuthorizationUser;

public interface AuthorizationService {
    AuthorizationUser authorize(String username, String password);
}
