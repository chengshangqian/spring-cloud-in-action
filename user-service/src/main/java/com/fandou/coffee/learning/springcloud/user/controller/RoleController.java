package com.fandou.coffee.learning.springcloud.user.controller;

import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.model.Role;
import com.fandou.coffee.learning.springcloud.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list/{userId}")
    public HttpResult<List<Role>> listByUserId(@PathVariable("userId") Integer userId){
        List<Role> roles = roleService.listByUserId(userId);
        return HttpResult.success(roles);
    }

    @GetMapping("/list/{apiId}")
    public HttpResult<List<Role>> listByApiId(@PathVariable("apiId") Integer apiId){
        List<Role> roles = roleService.listByApiId(apiId);
        return HttpResult.success(roles);
    }

    @GetMapping("/{id}")
    public HttpResult<Role> get(@PathVariable("id") Integer id){
        Role role = roleService.get(id);
        return HttpResult.success(role);
    }

    @GetMapping
    public HttpResult<List<Role>> list() {
        List<Role> roles = roleService.list();
        return HttpResult.success(roles);
    }

    @PostMapping
    public HttpResult<Integer> create(@RequestBody Role role){
        Integer count = roleService.create(role);
        return HttpResult.success(count);
    }

    @PutMapping
    public HttpResult<Integer> update(@RequestBody Role role){
        Integer count = roleService.update(role);
        return HttpResult.success(count);
    }

    @DeleteMapping("/{id}")
    public HttpResult<Integer> delete(@PathVariable("id") Integer id){
        Integer count = roleService.delete(id);
        return HttpResult.success(count);
    }
}
