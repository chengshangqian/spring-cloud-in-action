package com.fandou.coffee.learning.springcloud.log.service.impl;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.log.service.BlogService;
import com.fandou.coffee.learning.springcloud.log.service.LogService;
import com.fandou.coffee.learning.springcloud.log.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Override
    public HttpResult<List<Blog>> getBlogs(String accessToken, String username) {
        return blogService.getBlogs(accessToken,username);
    }

    @Override
    public HttpResult<User> getUser(String accessToken, String username) {
        return userService.getUser(accessToken,username);
    }
}
