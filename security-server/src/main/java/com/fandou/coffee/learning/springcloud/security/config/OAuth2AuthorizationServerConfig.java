package com.fandou.coffee.learning.springcloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 授权服务配置
 */
@Configuration
@EnableAuthorizationServer // 开启授权服务
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 数据源
    //@Autowired
    //@Qualifier("dataSource")
    //private DataSource dataSource;

    // 认证管理器
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 授权服务器安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //
                .allowFormAuthenticationForClients() // 允许表单认证
                .passwordEncoder(passwordEncoder);
    }

    /**
     * 客户端详情服务配置：配置客户端信息，存储在内存中，也可以存储到数据库中
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO 实现从数据库获取配置的客户端信息
        // clients.jdbc(dataSource).passwordEncoder(passwordEncoder);

        // 配置在内存中
        clients.inMemory()

                // user-service微服务客户端
                .withClient("user-service")
                .secret("$2a$12$3njFpV27MDo9ervf95ukiOJE7CG9Cm/25Qdj7DLqV6bKAPxP4k3pe") // 123456
                .authorizedGrantTypes("refresh_token","password")
                .accessTokenValiditySeconds(3600) // 有效期
                .scopes("service")

                // blog-service微服务客户端
                .and()
                .withClient("blog-service")
                .secret("$2a$12$3njFpV27MDo9ervf95ukiOJE7CG9Cm/25Qdj7DLqV6bKAPxP4k3pe") // 123456
                .authorizedGrantTypes("refresh_token","password")
                .accessTokenValiditySeconds(3600) // 有效期
                .scopes("service")

                // log-service微服务客户端：日志的调用是否需要开启
                .and()
                .withClient("log-service")
                .secret("$2a$12$3njFpV27MDo9ervf95ukiOJE7CG9Cm/25Qdj7DLqV6bKAPxP4k3pe") // 123456
                .authorizedGrantTypes("refresh_token","password")
                .accessTokenValiditySeconds(3600) // 有效期
                .scopes("service");
    }

    /**
     * 授权服务器终端配置：配置终端访问令牌的存储方式和身份认证管理
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .tokenEnhancer(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager);
    }
}
