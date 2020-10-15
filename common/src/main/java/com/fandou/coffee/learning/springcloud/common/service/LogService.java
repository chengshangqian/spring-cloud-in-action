package com.fandou.coffee.learning.springcloud.common.service;

import com.fandou.coffee.learning.springcloud.common.model.Log;

import java.util.List;

/**
 * 日志服务
 */
public interface LogService {
    /**
     * 根据id获取一条日志
     *
     * @param id
     * @return
     */
    Log get(Long id);

    /**
     * 列出所有日志
     *
     * @return
     */
    List<Log> list();

    /**
     * 创建一个新的日志
     *
     * @param log
     * @return
     */
    int create(Log log);

    /**
     * 更新一个日志
     *
     * @param log
     * @return
     */
    int update(Log log);

    /**
     * 根据id删除一个日志
     *
     * @param id
     * @return
     */
    int delete(Long id);
}
