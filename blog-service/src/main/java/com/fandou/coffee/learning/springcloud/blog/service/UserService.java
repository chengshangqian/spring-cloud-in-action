package com.fandou.coffee.learning.springcloud.blog.service;

import com.fandou.coffee.learning.springcloud.blog.service.impl.UserServiceMock;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service", fallback = UserServiceMock.class)
public interface UserService {
    @PostMapping("/users/{username}")
    HttpResult<User> getByUsername(@RequestHeader(value = "Authorization") String accessToken, @PathVariable("username") String username);
}
