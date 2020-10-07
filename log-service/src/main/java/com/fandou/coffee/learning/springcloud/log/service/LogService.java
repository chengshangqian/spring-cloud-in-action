package com.fandou.coffee.learning.springcloud.log.service;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;

import java.util.List;

public interface LogService {
    HttpResult<List<Blog>> getBlogs(String accessToken, String username);
    HttpResult<User> getUser(String accessToken, String username);
}
