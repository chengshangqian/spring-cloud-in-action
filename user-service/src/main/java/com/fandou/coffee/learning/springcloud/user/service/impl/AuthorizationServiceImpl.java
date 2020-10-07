package com.fandou.coffee.learning.springcloud.user.service.impl;

import com.fandou.coffee.learning.springcloud.common.exception.AuthorizationException;
import com.fandou.coffee.learning.springcloud.common.model.AccessToken;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.model.AuthorizationUser;
import com.fandou.coffee.learning.springcloud.user.service.JwtAuthorizationService;
import com.fandou.coffee.learning.springcloud.user.service.AuthorizationService;
import com.fandou.coffee.learning.springcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthorizationService jwtAuthorizationService;

    @Override
    public AuthorizationUser authorize(String username, String password) {
        // 查询用户
        User user = userService.getByUsername(username);

        if(null == user){
            throw new AuthorizationException("用户[" + username + "]不存在...");
        }

        // 校验密码
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new AuthorizationException("用户密码错误...");
        }

        // 如果用户存在，将请求授权服务器，为登录的用户申请用户服务授权（jwt访问令牌）
        // TODO 这些参数应该做成配置文件或数据库中
        String clientId = "user-service"; // 用户服务在授权服务器上注册的id
        String clientSecret = "123456"; // 用户服务在授权服务器上注册的认证密码
        String authorizationToken = Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes(Charset.forName("UTF-8")));
        String userServiceAuthorization = "Basic " + authorizationToken; // 将用户服务授权信息封装为认证请求头部
        String grantType = "password"; // 认证授权类型：在授权服务器上配置了refresh_token（刷新令牌）和password（密码方式认证授权）

        // 获取jwt访问令牌
        AccessToken jwtAccessToken = jwtAuthorizationService.getJwtAccessToken(userServiceAuthorization,grantType,username,password);

        // 获取失败或服务不可用（熔断）
        if(jwtAccessToken == null){
            throw new AuthorizationException("授权服务器异常...");
        }

        // 获取成功，封装为认证用户AuthenticationUser返回给客户端：后续客户端访问用户服务的所有资源（API或接口），只需要带上认证信息头部即可
        AuthorizationUser authorizationUser = new AuthorizationUser();
        authorizationUser.setAccessToken(jwtAccessToken);
        authorizationUser.setUser(user);

        return authorizationUser;
    }
}
