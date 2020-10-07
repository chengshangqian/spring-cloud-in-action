package com.fandou.coffee.learning.springcloud.user.service;

import com.fandou.coffee.learning.springcloud.common.model.AccessToken;
import com.fandou.coffee.learning.springcloud.user.service.impl.JwtAuthorizationServiceMock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 安全服务认证授权接口
 */
@FeignClient(value = "security-server",fallback = JwtAuthorizationServiceMock.class)
public interface JwtAuthorizationService {
    /**
     * 申请授权：申请访问令牌
     *
     * @param userServiceAuthorization Authorization头部，携带的是被保护资源即用户服务(user-service)客户端在安全认证授权服务器上
     *                                       注册的认证信息，包括client_id和client_secret等
     *
     * @param type
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/oauth/token")
    AccessToken getJwtAccessToken(@RequestHeader("Authorization") String userServiceAuthorization
            , @RequestParam("grant_type") String type
            , @RequestParam("username") String username
            , @RequestParam("password") String password);
}
