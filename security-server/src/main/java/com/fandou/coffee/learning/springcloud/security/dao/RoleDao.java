package com.fandou.coffee.learning.springcloud.security.dao;

import com.fandou.coffee.learning.springcloud.common.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("SELECT r.* FROM role r,user_role ur WHERE r.id = ur.role_id AND ur.user_id = #{userId}")
    List<Role> listByUserId(Integer userId);
}
