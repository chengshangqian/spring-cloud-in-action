package com.fandou.coffee.learning.springcloud.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Log implements Serializable {
    // 序列化id
    private static final long serialVersionUID = -1000003L;
    private Long id;
    private Date createDate;
    private String ip;
    private String method;
    private String operation;
    private String params;
    private String username;
}
