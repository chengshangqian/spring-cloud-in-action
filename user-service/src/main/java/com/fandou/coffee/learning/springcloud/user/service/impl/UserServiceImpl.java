package com.fandou.coffee.learning.springcloud.user.service.impl;

import com.fandou.coffee.learning.springcloud.common.exception.BaseException;
import com.fandou.coffee.learning.springcloud.common.exception.ExceptionCode;
import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.utils.UserUtils;
import com.fandou.coffee.learning.springcloud.user.dao.UserDao;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.user.model.UserBlogDTO;
import com.fandou.coffee.learning.springcloud.user.service.BlogService;
import com.fandou.coffee.learning.springcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BlogService blogService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getByUsername(username);
    }

    @Override
    public User get(Integer id){
        return userDao.get(id);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public List<User> list(){
        return userDao.list();
    }

    @Override
    public int create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.create(user);
    }

    @Override
    public int update(User user){
        return userDao.update(user);
    }

    @Override
    public int updatePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.updatePassword(user);
    }

    @Override
    public UserBlogDTO getBlogs(String username) {
        User user = userDao.getByUsername(username);
        if(null != user){
            HttpResult<List<Blog>> httpResult = blogService.getBlogs(UserUtils.getCurrentToken(),username);
            if (httpResult == null) {
                throw new BaseException(ExceptionCode.RPC_ERROR);
            }

            UserBlogDTO userBlogDTO = new UserBlogDTO();
            userBlogDTO.setUser(user);
            userBlogDTO.setBlogs(httpResult.data);

            System.out.println("UserBlogDTO ===> " + userBlogDTO);
            return userBlogDTO;
        }
        return null;
    }

    @Override
    public int delete(Integer id){
        return userDao.delete(id);
    }
}
