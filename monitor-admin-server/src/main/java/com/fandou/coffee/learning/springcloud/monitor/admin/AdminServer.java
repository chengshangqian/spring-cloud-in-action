package com.fandou.coffee.learning.springcloud.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAdminServer // 开启SpringBootAdmin
@EnableDiscoveryClient // 开启服务发现，注册为服务
public class AdminServer {
    public static void main(String[] args) {
        SpringApplication.run(AdminServer.class,args);
    }
}
