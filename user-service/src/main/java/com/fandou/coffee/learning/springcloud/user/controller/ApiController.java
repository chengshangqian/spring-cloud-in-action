package com.fandou.coffee.learning.springcloud.user.controller;

import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.model.Api;
import com.fandou.coffee.learning.springcloud.common.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/{id}")
    public HttpResult<Api> get(@PathVariable("id") Integer id){
        Api api = apiService.get(id);
        return HttpResult.success(api);
    }

    @GetMapping
    public HttpResult<List<Api>> list() {
        List<Api> list = apiService.list();
        return HttpResult.success(list);
    }

    @PostMapping
    public HttpResult<Integer> create(@RequestBody Api api){
        Integer count = apiService.create(api);
        return HttpResult.success(count);
    }

    @PutMapping
    public HttpResult<Integer> update(@RequestBody Api api){
        Integer count = apiService.update(api);
        return HttpResult.success(count);
    }

    @DeleteMapping("/{id}")
    public HttpResult<Integer> delete(@PathVariable("id") Integer id){
        Integer count = apiService.delete(id);
        return HttpResult.success(count);
    }
}
