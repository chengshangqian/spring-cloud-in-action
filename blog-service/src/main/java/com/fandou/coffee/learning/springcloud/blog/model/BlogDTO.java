package com.fandou.coffee.learning.springcloud.blog.model;

import com.fandou.coffee.learning.springcloud.common.model.Blog;
import com.fandou.coffee.learning.springcloud.common.model.User;
import lombok.Data;

@Data
public class BlogDTO {
    private Blog blog;
    private User user;
}
