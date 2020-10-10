package com.fandou.coffee.learning.springcloud.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}) // 本地不做用户认证，排除用户详情服务自动装配
@EnableDiscoveryClient // 开启服务注册和发现
@EnableFeignClients // 开启Feign
@EnableHystrix // 开启熔断器和熔断监控
@EnableHystrixDashboard
public class BlogServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApp.class, args);
    }
}
