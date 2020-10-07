package com.fandou.coffee.learning.springcloud.user.service.impl;

import com.fandou.coffee.learning.springcloud.common.service.ApiService;
import com.fandou.coffee.learning.springcloud.user.dao.ApiDao;
import com.fandou.coffee.learning.springcloud.common.model.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public Api get(Integer id){
        return apiDao.get(id);
    }

    @Override
    public List<Api> list() {
        return apiDao.list();
    }

    @Override
    public int create(Api api){
        return apiDao.create(api);
    }

    @Override
    public int update(Api api){
        return apiDao.update(api);
    }

    @Override
    public int delete(Integer id){
        return apiDao.delete(id);
    }
}
