package com.fandou.coffee.learning.springcloud.log.service.impl;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.log.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客服务熔断处理类
 */
@Service
public class BlogServiceMock implements BlogService {
    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceMock.class);

    @Override
    public HttpResult<List<Blog>> getBlogs(String accessToken, String username) {

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("博客服务熔断被开启 => {accessToken -> {},username -> {}}",accessToken,username);
        }

        return null;
    }
}
