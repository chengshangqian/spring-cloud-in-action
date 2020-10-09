package com.fandou.coffee.learning.springcloud.monitor.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * WebSecurity配置类：继承WebSecurityConfigurerAdapter，覆盖需要自定义的方法即可
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 上下文
    private final String adminContextPath;

    // 使用数据库查询用户详情
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 密码编码器：认证时，用于对输入的密码进行编码，然后和数据库中的密码进行比较
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 长度16会很影响性能，可以使用默认10
        return new BCryptPasswordEncoder(12);
    }

    // 通过构造器获取上下文
    public WebSecurityConfig(AdminServerProperties adminServerProperties){
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    /**
     * 配置Http请求的认证规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 认证成功处理器：跳转回到登录前的界面
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setTargetUrlParameter("redirectTo");

        http
                // 认证授权规则
                .authorizeRequests(authorizeConfigurer -> authorizeConfigurer
                        // 静态资源和登录API不需要认证
                        .antMatchers(adminContextPath+"/assets/**").permitAll()
                        .antMatchers(adminContextPath+"/login/**").permitAll()

                        // 健康监控API（/actuator/**）
                        // 使用服务发现时，spring-boot-admin应用本身也会被监控，同时如果spring-boot-admin启用了spring-security，
                        // 1.此时需要配置一个账号密码给actuator模块调用健康监控API，不管是账号密码存放在内存的方式还是数据库查询方式，否则应用在spring-boot-admin后台会一直显示为离线实例，
                        // 配置账号密码方式通过eureka.instance.metadata-map.user.name和eureka.instance.metadata-map.user.password指定；
                        //.antMatchers(adminContextPath+"/actuator/**").hasAnyRole("SYSTEM","ADMIN","TM") // 限定角色
                        // 2.要么健康监控API设置为不需要认证，如下
                        .antMatchers(adminContextPath+"/actuator/**").permitAll() // 不需要认证

                        // 剩下的请求都需要认证，但不限制角色
                        .anyRequest().authenticated()
                )

                // 登录
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage(adminContextPath+"/login")
                        .successHandler(handler)
                )

                // 记住我
                .rememberMe(rememberMeConfigurer -> rememberMeConfigurer
                        .key("rmb-me")
                        .rememberMeCookieName("rmb-me")
                        //.tokenValiditySeconds(60 * 60 * 24 * 7) // 有效期1个星期
                        .userDetailsService(userDetailsService)
                )

                // 登出
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl(adminContextPath+"/logout")
                )

                // 跨域攻击
                .csrf(csrfConfigurer -> csrfConfigurer
                        .disable()
                )

                // 认证方式
                .httpBasic();
    }
}
