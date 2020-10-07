package com.fandou.coffee.learning.springcloud.common.model;

import lombok.Data;

/**
 * 认证用户
 */
@Data
public class AuthorizationUser {
    // 用户
    private User user;
    // 认证通过后的JWT访问令牌
    private AccessToken accessToken;
}
