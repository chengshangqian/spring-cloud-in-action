package com.fandou.coffee.learning.springcloud.blog.dao;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogDao {
    @Select("SELECT id,subject,title,username FROM blog WHERE id = #{id}")
    Blog get(Integer id);

    @Select("SELECT id,subject,title,username FROM blog")
    List<Blog> list();

    @Insert("INSERT INTO blog (subject,title,username) VALUES (#{subject},#{title},#{username})")
    int create(Blog blog);

    @Update("UPDATE blog SET subject = #{subject},title = #{title},username = #{username} WHERE id = #{id}")
    int update(Blog blog);

    @Delete("DELETE FROM blog WHERE id = #{id}")
    int delete(Integer id);

    @Select("SELECT id,subject,title,username FROM blog where username = #{username}")
    List<Blog> listBlogs(String username);
}
