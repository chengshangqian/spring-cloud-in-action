package com.fandou.coffee.learning.springcloud.common.service;

import com.fandou.coffee.learning.springcloud.common.model.Blog;

import java.util.List;

public interface BlogService {
    /**
     * 根据id获取Blog
     *
     * @param id
     * @return
     */
    Blog get(Integer id);

    /**
     * 列出所有Blog
     *
     * @return
     */
    List<Blog> list();

    /**
     * 创建一个新的Blog
     *
     * @param api
     * @return
     */
    int create(Blog api);

    /**
     * 更新一个Blog
     *
     * @param api
     * @return
     */
    int update(Blog api);

    /**
     * 根据id删除一个Blog
     *
     * @param id
     * @return
     */
    int delete(Integer id);
}
