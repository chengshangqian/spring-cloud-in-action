package com.fandou.coffee.learning.springcloud.user.service;

import com.fandou.coffee.learning.springcloud.common.model.Role;

import java.util.List;

/**
 * 角色服务
 */
public interface RoleService extends com.fandou.coffee.learning.springcloud.common.service.RoleService{
    /**
     * 列出指定用户的所有角色
     *
     * @param userId
     * @return
     */
    List<Role> listByUserId(Integer userId);

    /**
     * 列出指定API的归属的角色
     *
     * @param apiId
     * @return
     */
    List<Role> listByApiId(Integer apiId);
}
