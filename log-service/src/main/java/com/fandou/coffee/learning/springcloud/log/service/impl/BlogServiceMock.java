package com.fandou.coffee.learning.springcloud.log.service.impl;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.log.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客服务熔断处理类
 */
@Service
public class BlogServiceMock implements BlogService {
    @Override
    public HttpResult<List<Blog>> getBlogs(String accessToken, String username) {
        System.out.println("博客服务熔断被开启...");
        return null;
    }
}
