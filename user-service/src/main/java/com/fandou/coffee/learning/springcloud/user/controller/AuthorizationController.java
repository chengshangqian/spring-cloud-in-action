package com.fandou.coffee.learning.springcloud.user.controller;

import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.model.AuthorizationUser;
import com.fandou.coffee.learning.springcloud.user.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录认证控制器
 */
@RestController
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 用户认证：本地登录后，调用安全认证服务对登录用户进行认证授权，之后方可调用其它API资源
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/authorize")
    public HttpResult<AuthorizationUser> authorize(@RequestParam("username") String username, @RequestParam("password") String password){
        AuthorizationUser authorizationUser = authorizationService.authorize(username, password);
        return HttpResult.success(authorizationUser);
    }
}
