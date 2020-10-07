package com.fandou.coffee.learning.springcloud.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable {
    // 序列化id
    private static final long serialVersionUID = -1000002L;
    private int id;
    private String subject;
    private String title;
    private String username;
}
