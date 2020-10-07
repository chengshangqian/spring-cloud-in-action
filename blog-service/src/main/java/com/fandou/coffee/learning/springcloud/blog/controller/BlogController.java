package com.fandou.coffee.learning.springcloud.blog.controller;

import com.fandou.coffee.learning.springcloud.blog.model.BlogDTO;
import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@PreAuthorize("hasAnyRole('USER','ADMIN','TM')") // 拥有其中的任一角色即可调用API
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public HttpResult<Blog> get(@PathVariable("id") Integer id) {
        Blog blog = blogService.get(id);
        return HttpResult.success(blog);
    }

    /**
     * 获取博客详情：包含博主信息
     * 内部调用远程服务获取博主信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/detail")
    public HttpResult<BlogDTO> getBlog(@PathVariable("id") Integer id) {
        BlogDTO blog = blogService.getBlog(id);
        return HttpResult.success(blog);
    }

    /**
     * 获取指定用户发表的所有博客
     *
     * @param username
     * @return
     */
    @PostMapping("/{username}")
    public HttpResult<List<Blog>> getBlogs(@PathVariable("username") String username) {
        List<Blog> blogs = blogService.getBlogs(username);
        return HttpResult.success(blogs);
    }

    @GetMapping
    public HttpResult<List<Blog>> list() {
        List<Blog> blogs = blogService.list();
        return HttpResult.success(blogs);
    }

    @PostMapping
    public HttpResult<Integer> create(@RequestBody Blog blog) {
        Integer count = blogService.create(blog);
        return HttpResult.success(count);
    }

    @PutMapping
    public HttpResult<Integer> update(@RequestBody Blog blog) {
        Integer count = blogService.update(blog);
        return HttpResult.success(count);
    }

    @DeleteMapping("/{id}")
    public HttpResult<Integer> delete(@PathVariable("id") Integer id) {
        Integer count = blogService.delete(id);
        return HttpResult.success(count);
    }
}
