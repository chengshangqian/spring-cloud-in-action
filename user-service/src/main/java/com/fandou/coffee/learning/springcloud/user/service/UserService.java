package com.fandou.coffee.learning.springcloud.user.service;

import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.user.model.UserBlogDTO;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService extends com.fandou.coffee.learning.springcloud.common.service.UserService {

    /**
     * 根据username获取用户
     *
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 更新用户密码
     *
     * @param user
     * @return
     */
    int updatePassword(User user);

    /**
     * 获取指定用户及其发表的所有博客
     *
     * @param username
     * @return
     */
    UserBlogDTO getBlogs(String username);
}
