package com.fandou.coffee.learning.springcloud.log.dao;

import com.fandou.coffee.learning.springcloud.common.model.Log;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogDao {

    @Select("SELECT id,ip,method,operation,params,username,create_date FROM sys_log WHERE id = #{id}")
    Log get(Long id);

    @Select("SELECT id,ip,method,operation,params,username,create_date FROM sys_log")
    List<Log> list();

    @Insert("INSERT INTO sys_log (ip,method,operation,params,username,create_date) VALUES (#{ip},#{method},#{operation},#{params},#{username},#{createDate})")
    int create(Log log);

    @Update("UPDATE sys_log SET method = #{method},operation = #{operation},params = #{params},username = #{username} WHERE id = #{id}")
    int update(Log log);

   @Delete("DELETE FROM sys_log WHERE id = #{id}")
    int delete(Long id);
}
