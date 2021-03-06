package com.fandou.coffee.learning.springcloud.user.dao;

import com.fandou.coffee.learning.springcloud.common.model.Api;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApiDao {

    @Select("SELECT id,name,path,path_ant_pattern FROM api WHERE id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="path",property="path"),
            @Result(column="path_ant_pattern",property="pathAntPattern"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="com.fandou.coffee.learning.springcloud.user.dao.RoleDao.listByApiId"
                    )
            )
    })
    Api get(Integer id);

    @Select("SELECT id,name,path,path_ant_pattern FROM api")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="path",property="path"),
            @Result(column="path_ant_pattern",property="pathAntPattern"),
            @Result(column="id",property="authorities",
                    many=@Many(
                            select="com.fandou.coffee.learning.springcloud.user.dao.RoleDao.listByApiId"
                    )
            )
    })
    List<Api> list();

    @Insert("INSERT INTO api (name,path,path_ant_pattern) VALUES (#{name},#{path},#{pathAntPattern})")
    int create(Api api);

    @Update("UPDATE api SET name = #{name},path = #{path},path_ant_pattern = #{pathAntPattern} WHERE id = #{id}")
    int update(Api api);

    @Delete("DELETE FROM api WHERE id = #{id}")
    int delete(Integer id);
}
