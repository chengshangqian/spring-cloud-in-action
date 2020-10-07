package com.fandou.coffee.learning.springcloud.monitor.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * WebSecurity配置类：继承WebSecurityConfigurerAdapter，覆盖需要自定义的方法即可
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 上下文
    private final String adminContextPath;

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
                        .antMatchers(adminContextPath+"/assets/**").permitAll()
                        .antMatchers(adminContextPath+"/login/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 登录
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage(adminContextPath+"/login")
                        .successHandler(handler)
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
