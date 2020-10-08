package com.fandou.coffee.learning.springcloud.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}) // 本地不做用户认证，排除用户详情服务自动装配
@EnableEurekaClient // 开启Eureka客户端：注册和发现服务
@EnableFeignClients // 开启Feign
@EnableHystrix // 开启熔断器和熔断监控
@EnableHystrixDashboard
public class LogServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(LogServiceApp.class,args);
    }
}
