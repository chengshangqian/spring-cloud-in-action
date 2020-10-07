package com.fandou.coffee.learning.springcloud.user.service.impl;

import com.fandou.coffee.learning.springcloud.user.dao.RoleDao;
import com.fandou.coffee.learning.springcloud.common.model.Role;
import com.fandou.coffee.learning.springcloud.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> listByUserId(Integer userId){
        return roleDao.listByUserId(userId);
    }

    @Override
    public List<Role> listByApiId(Integer apiId){
        return roleDao.listByApiId(apiId);
    }

    @Override
    public Role get(Integer id){
        return roleDao.get(id);
    }

    @Override
    public List<Role> list() {
        return roleDao.list();
    }

    @Override
    public int create(Role role){
        return roleDao.create(role);
    }

    @Override
    public int update(Role role){
        return roleDao.update(role);
    }

    @Override
    public int delete(Integer id){
        return roleDao.delete(id);
    }
}
