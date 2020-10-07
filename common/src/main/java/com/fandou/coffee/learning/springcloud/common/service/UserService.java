package com.fandou.coffee.learning.springcloud.common.service;

import com.fandou.coffee.learning.springcloud.common.model.User;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User get(Integer id);

    /**
     * 列出所有用户
     *
     * @return
     */
    List<User> list();

    /**
     * 创建一个新的用户
     *
     * @param api
     * @return
     */
    int create(User api);

    /**
     * 更新一个用户
     *
     * @param api
     * @return
     */
    int update(User api);

    /**
     * 根据id删除一个用户
     *
     * @param id
     * @return
     */
    int delete(Integer id);
}
