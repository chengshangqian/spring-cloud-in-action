package com.fandou.coffee.learning.springcloud.blog.service.impl;

import com.fandou.coffee.learning.springcloud.blog.dao.BlogDao;
import com.fandou.coffee.learning.springcloud.blog.model.BlogDTO;
import com.fandou.coffee.learning.springcloud.blog.service.UserService;
import com.fandou.coffee.learning.springcloud.common.exception.BaseException;
import com.fandou.coffee.learning.springcloud.common.exception.ExceptionCode;
import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.blog.service.BlogService;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private UserService userService;

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog get(Integer id) {
        return blogDao.get(id);
    }

    @Override
    public BlogDTO getBlog(Integer id) {
        Blog blog = blogDao.get(id);
        if (null != blog) {
            HttpResult<User> httpResult = userService.getByUsername(UserUtils.getCurrentToken(), blog.getUsername());
            if (httpResult == null) {
                throw new BaseException(ExceptionCode.RPC_ERROR);
            }

            System.out.println("httpResult => " + httpResult);

            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setBlog(blog);
            blogDTO.setUser(httpResult.data);
            return blogDTO;
        }
        return null;
    }

    @Override
    public List<Blog> getBlogs(String username) {
        return blogDao.listBlogs(username);
    }

    @Override
    public List<Blog> list() {
        return blogDao.list();
    }

    @Override
    public int create(Blog blog) {
        return blogDao.create(blog);
    }

    @Override
    public int update(Blog blog) {
        return blogDao.update(blog);
    }

    @Override
    public int delete(Integer id) {
        return blogDao.delete(id);
    }
}
