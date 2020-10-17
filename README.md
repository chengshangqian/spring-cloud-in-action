# 1、SpringCloud项目实战

## 1.1、概述

使用SpringCloud组件，搭建一个简单的微博微服务系统，支持用户注册、登录，发微博，查看微博列表、微博详情等，整个系统提供微博服务、用户服务，以及日志服务。由配置中心、服务注册与发现中心、认证授权中心、应用监控中心、服务熔断监控中心、服务网关、三大服务等构成。

通过整合SpringCloud中的各个组件，更体系的理解SpringCloud各个组件的使用。

## 1.2、组件应用

用到的SpringCloud的组件包括Spring Cloud Netfix Eureka、Spring Cloud Config、Spring Cloud Security、Spring Cloud Security OAuth2、Spring Cloud Netfix Hystrix、Spring Cloud Netfix Hystrix Dashboard、Spring Cloud Netfix Turbine、Spring Cloud Gateway、Spring Cloud Sleuth等，相关的SpringBoot组件包括Spring Boot Actuator、Spring Boot Admin等，其它的Spring模块还有Spring Security、Spring Web MVC等。

## 1.3、工程结构

eureka-server：服务注册与发现中心

config-server：配置中心

common：公共组件

security-server：认证授权中心

user-service：用户服务，包括角色

blog-service：博客服务

log-service：日志服务

monitor-admin-server：应用监控中心

monitor-turbine-server：服务熔断监控中心

monitor-sleuth-server：链路跟踪

gateway-server：服务网关

gateway-zuul-server*：路由网关（Zuul）

service-registry-server-consul*：服务注册于发现中心（Consul）

# 2、服务注册与发现中心eureka-server

# 3、配置中心config-server

# 4、公共组件common

定义整个微服务系统的实体模型、增删改接口、Http请求结果、异常类、工具类等。

# 5、认证授权中心security-server

# 6、用户服务user-service

# 7、博客服务blog-service

# 8、日志服务log-service

# 9、应用监控中心monitor-admin-server

# 10、服务熔断监控中心monitor-turbine-server

# 11、链路跟踪monitor-sleuth-server

# 12、服务网关gateway-server

# 13、gateway-zuul-server*

# 14、service-registry-server-consul*

# 15、测试

