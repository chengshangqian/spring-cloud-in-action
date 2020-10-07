package com.fandou.coffee.learning.springcloud.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient // 开启Eureka客户端：注册和发现服务
@EnableFeignClients // 开启Feign
@EnableHystrix // 开启熔断器和熔断监控
@EnableHystrixDashboard
public class BlogServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApp.class, args);
    }
}
