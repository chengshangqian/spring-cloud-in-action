package com.fandou.coffee.learning.springcloud.user.model;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserBlogDTO {
    private User user;
    private List<Blog> blogs;
}
