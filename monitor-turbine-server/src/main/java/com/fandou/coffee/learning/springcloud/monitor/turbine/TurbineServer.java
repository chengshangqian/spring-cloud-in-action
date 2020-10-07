package com.fandou.coffee.learning.springcloud.monitor.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient // 开启Eureka
@EnableHystrix // 开启Hystrix及监控
@EnableHystrixDashboard
@EnableTurbine // 开启Turbine
public class TurbineServer {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServer.class,args);
    }
}
