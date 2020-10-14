package com.fandou.coffee.learning.springcloud.security.dao;

import com.fandou.coffee.learning.springcloud.common.model.Role;
import com.fandou.coffee.learning.springcloud.common.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserAuthenticationDao {
    @Select("SELECT id,name,phone,username,password FROM user WHERE username = #{username}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="phone",property="phone"),
            @Result(column="username",property="username"),
            @Result(column="password",property="password"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="selectRoles"
                    )
            )
    })
    User getByUsername(String username);

    @Select("SELECT r.* FROM role r,user_role ur WHERE r.id = ur.role_id AND ur.user_id = #{id}")
    List<Role> selectRoles(Integer id);
}
