package com.fandou.coffee.learning.springcloud.gateway.config;

import com.fandou.coffee.learning.springcloud.gateway.resolver.HostAddrKeyResolver;
import com.fandou.coffee.learning.springcloud.gateway.resolver.UriKeyResolver;
import com.fandou.coffee.learning.springcloud.gateway.resolver.UserKeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GatewayConfig {

    /**
     * 根据请求uri进行服务限流
     *
     * @return
     */
    @Bean
    @Primary
    public UriKeyResolver uriKeyResolver(){
        return new UriKeyResolver();
    }

    /**
     * 根据主机进行服务限流
     *
     * @return
     */
    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver (){
        return new HostAddrKeyResolver();
    }

    /**
     * 根据用户进行服务限流
     *
     * @return
     */
    @Bean
    public UserKeyResolver userKeyResolver (){
        return new UserKeyResolver();
    }
}
