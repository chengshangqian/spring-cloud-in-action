package com.fandou.coffee.learning.springcloud.common.service;

import com.fandou.coffee.learning.springcloud.common.model.Api;

import java.util.List;

/**
 * API服务
 */
public interface ApiService {
    /**
     * 根据id获取API
     *
     * @param id
     * @return
     */
    Api get(Integer id);

    /**
     * 列出所有API
     *
     * @return
     */
    List<Api> list();

    /**
     * 创建一个新的API
     *
     * @param api
     * @return
     */
    int create(Api api);

    /**
     * 更新一个API
     *
     * @param api
     * @return
     */
    int update(Api api);

    /**
     * 根据id删除一个API
     *
     * @param id
     * @return
     */
    int delete(Integer id);
}
