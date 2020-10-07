package com.fandou.coffee.learning.springcloud.user.controller;

import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.user.model.UserBlogDTO;
import com.fandou.coffee.learning.springcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public HttpResult<User> get(@PathVariable("id") Integer id){
        User user = userService.get(id);
        return HttpResult.success(user);
    }

    /**
     * 调用博客服务
     *
     * @param username
     * @return
     */
    @PostMapping("/blogs/{username}")
    public HttpResult<UserBlogDTO> getBlogs(@PathVariable("username") String username) {
        UserBlogDTO userBlogDTO = userService.getBlogs(username);
        return HttpResult.success(userBlogDTO);
    }

    /**
     * Blog模块调用
     *
     * @param username
     * @return
     */
    @PostMapping("/{username}")
    public HttpResult<User> getByUsername(@PathVariable("username") String username) {
        User user = userService.getByUsername(username);
        System.out.println("user => " + user);
        return HttpResult.success(user);
    }

    /**
     * 获取当前认证通过主体信息
     *
     * @param principal
     * @return
     */
    @GetMapping("/current")
    public HttpResult<Principal> get(Principal principal){
        return HttpResult.success(principal);
    }

    @GetMapping
    public HttpResult<List<User>> list(){
        List<User> users = userService.list();
        return HttpResult.success(users);
    }

    @PostMapping
    public HttpResult<Integer> create(@RequestBody User user){
        Integer count = userService.create(user);
        return HttpResult.success(count);
    }

    @PutMapping
    public HttpResult<Integer> update(@RequestBody User user){
        Integer count = userService.update(user);
        return HttpResult.success(count);
    }

    @PutMapping("/password")
    public HttpResult<Integer> updatePassword(@RequestBody User user){
        Integer count = userService.updatePassword(user);
        return HttpResult.success(count);
    }

    @DeleteMapping("/{id}")
    public HttpResult<Integer> delete(@PathVariable("id") Integer id){
        Integer count = userService.delete(id);
        return HttpResult.success(count);
    }
}
