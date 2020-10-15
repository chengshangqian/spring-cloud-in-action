package com.fandou.coffee.learning.springcloud.log.controller;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.Log;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.log.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日志服务
 */
@RestController
@RequestMapping("/logs")
public class LogController {
    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

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

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("getBlogs::Authorization => ：{}",accessToken);
            LOGGER.debug("getBlogs::username => ：{}",username);
        }

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

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("getUser::Authorization => ：{}",accessToken);
            LOGGER.debug("getUser::username => ：{}",username);
        }

        return logService.getUser(accessToken,username);
    }

    @GetMapping("/{id}")
    public HttpResult<Log> get(@RequestParam("id") Long id) {
        return HttpResult.success(logService.get(id));
    }

    @GetMapping
    public HttpResult<List<Log>> list() {
        return HttpResult.success(logService.list());
    }

    @PostMapping
    public HttpResult<Integer> create(@RequestBody Log log) {
        return HttpResult.success(logService.create(log));
    }

    @PutMapping
    public HttpResult<Integer> update(@RequestBody Log log) {
        return HttpResult.success(logService.update(log));
    }

    @DeleteMapping("/{id}")
    public HttpResult<Integer> delete(@RequestParam("id") Long id) {
        return HttpResult.success(logService.delete(id));
    }
}
