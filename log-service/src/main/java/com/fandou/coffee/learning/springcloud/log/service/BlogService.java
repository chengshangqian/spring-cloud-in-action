package com.fandou.coffee.learning.springcloud.log.service;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.log.service.impl.BlogServiceMock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 博客服务接口
 */
@FeignClient(value = "blog-service", fallback = BlogServiceMock.class)
public interface BlogService {
    /**
     * 获取指定用户发表的所有博客
     *
     * @param accessToken Authorization请求头：用户认证登录后生成的jwt访问令牌
     * @param username
     * @return
     */
    @PostMapping("/blogs/{username}")
    HttpResult<List<Blog>> getBlogs(@RequestHeader(value = "Authorization") String accessToken, @PathVariable("username") String username);
}
