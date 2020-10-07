package com.fandou.coffee.learning.springcloud.security.dao;

import com.fandou.coffee.learning.springcloud.common.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("SELECT id,name,phone,username,password FROM user WHERE username = #{username}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="phone",property="phone"),
            @Result(column="username",property="username"),
            @Result(column="password",property="password"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="com.fandou.coffee.learning.springcloud.security.dao.RoleDao.listByUserId"
                    )
            )
    })
    User getByUsername(String username);
}
