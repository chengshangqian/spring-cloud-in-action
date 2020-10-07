package com.fandou.coffee.learning.springcloud.user.dao;

import com.fandou.coffee.learning.springcloud.common.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT id,name,phone,username,password FROM user WHERE id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="phone",property="phone"),
            @Result(column="username",property="username"),
            @Result(column="password",property="password"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="com.fandou.coffee.learning.springcloud.user.dao.RoleDao.listByUserId"
                    )
            )
    })
    User get(Integer id);

    @Select("SELECT id,name,phone,username,password FROM user WHERE username = #{username}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="phone",property="phone"),
            @Result(column="username",property="username"),
            @Result(column="password",property="password"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="com.fandou.coffee.learning.springcloud.user.dao.RoleDao.listByUserId"
                    )
            )
    })
    User getByUsername(String username);

    @Select("SELECT id,name,phone,username,password FROM user")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="phone",property="phone"),
            @Result(column="username",property="username"),
            @Result(column="password",property="password"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="com.fandou.coffee.learning.springcloud.user.dao.RoleDao.listByUserId"
                    )
            )
    })
    List<User> list();

    @Insert("INSERT INTO user (name,phone,username,password) VALUES (#{name},#{phone},#{username},#{password})")
    int create(User user);

    @Update("UPDATE user SET name = #{name},phone = #{phone} WHERE id = #{id}")
    int update(User user);

    @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
    int updatePassword(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int delete(Integer id);
}