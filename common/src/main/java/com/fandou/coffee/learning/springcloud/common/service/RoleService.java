package com.fandou.coffee.learning.springcloud.common.service;

import com.fandou.coffee.learning.springcloud.common.model.Role;

import java.util.List;

/**
 * 角色服务
 */
public interface RoleService {
    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    Role get(Integer id);

    /**
     * 列出所有角色
     *
     * @return
     */
    List<Role> list();

    /**
     * 创建一个新的角色
     *
     * @param api
     * @return
     */
    int create(Role api);

    /**
     * 更新一个角色
     *
     * @param api
     * @return
     */
    int update(Role api);

    /**
     * 根据id删除一个角色
     *
     * @param id
     * @return
     */
    int delete(Integer id);
}
