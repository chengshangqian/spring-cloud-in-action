package com.fandou.coffee.learning.springcloud.log.controller;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.log.service.BlogService;
import com.fandou.coffee.learning.springcloud.log.service.LogService;
import com.fandou.coffee.learning.springcloud.log.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日志服务
 */
@RestController
@RequestMapping("/logs")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 调用博客服务：获取指定用户发表的博客列表
     *
     * @param username
     * @return
     */
    @PostMapping("/blogs/{username}")
    public HttpResult<List<Blog>> getBlogs(@RequestHeader(value = "Authorization") String accessToken,@PathVariable("username") String username) {
        System.out.println("getBlogs::Authorization => " + accessToken);
        System.out.println("getBlogs::username => " + username);
        return logService.getBlogs(accessToken,username);
    }

    /**
     * 调用用户服务：获取指定用户详情
     *
     * @param username
     * @return
     */
    @PostMapping("/user/{username}")
    public HttpResult<User> getUser(@RequestHeader(value = "Authorization") String accessToken, @PathVariable("username") String username) {
        System.out.println("getUser::Authorization => " + accessToken);
        System.out.println("getUser::username => " + username);
        return logService.getUser(accessToken,username);
    }
}
