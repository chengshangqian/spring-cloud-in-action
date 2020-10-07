package com.fandou.coffee.learning.springcloud.blog.service;

import com.fandou.coffee.learning.springcloud.blog.model.BlogDTO;
import com.fandou.coffee.learning.springcloud.common.model.Blog;

import java.util.List;

public interface BlogService extends com.fandou.coffee.learning.springcloud.common.service.BlogService {
    /**
     * 获取指定博客详情：包含博主信息
     *
     * @param id
     * @return
     */
    BlogDTO getBlog(Integer id);

    /**
     * 获取指定用户发表的所有博客
     *
     * @param username
     * @return
     */
    List<Blog> getBlogs(String username);
}
