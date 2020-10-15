package com.fandou.coffee.learning.springcloud.log.service.impl;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.Log;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.support.IdGenerator;
import com.fandou.coffee.learning.springcloud.log.dao.LogDao;
import com.fandou.coffee.learning.springcloud.log.service.BlogService;
import com.fandou.coffee.learning.springcloud.log.service.LogService;
import com.fandou.coffee.learning.springcloud.log.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    // 分布式id
    private static final String LOG_ID_KEY = "id-vblog-sys-log";

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogDao logDao;

    @Override
    public HttpResult<List<Blog>> getBlogs(String accessToken, String username) {
        return blogService.getBlogs(accessToken,username);
    }

    @Override
    public HttpResult<User> getUser(String accessToken, String username) {
        return userService.getUser(accessToken,username);
    }

    @Override
    public Log get(Long id) {
        assert id != null;
        return logDao.get(id);
    }

    @Override
    public List<Log> list() {
        return logDao.list();
    }

    @Override
    public int create(Log log) {
        assert log != null;

        //log.setId(idGenerator.id(LOG_ID_KEY));

        if(log.getCreateDate() == null) {
            log.setCreateDate(new Date());
        }

        return logDao.create(log);
    }

    @Override
    public int update(Log log) {
        assert log != null;
        return logDao.update(log);
    }

    @Override
    public int delete(Long id) {
        assert id != null;
        return logDao.delete(id);
    }
}
