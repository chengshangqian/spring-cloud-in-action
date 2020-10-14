package com.fandou.coffee.learning.springcloud.security.controller;

import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.security.support.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oauth2/clientdetails")
public class OAuth2ClientDetailsController {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2ClientDetailsController.class);

    /**
     * 获取id
     */
    @Autowired
    private IdGenerator idGenerator;

    // 客户端表的id键
    private static final String CLIENT_DETAILS_ID_KEY = "id-vblog-clientdetails";

    @Autowired
    @Qualifier("jdbcClientDetailsService")
    public ClientRegistrationService clientRegistrationService;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.address}")
    private String mysqlHost;

    // 测试：创建分布式id
    @GetMapping("/id")
    public HttpResult<Long> createClientId(){
        return HttpResult.success(idGenerator.id(CLIENT_DETAILS_ID_KEY));
    }

    // 测试
    @GetMapping("/info")
    public HttpResult<String> info(){
        String info = String.format("redisHost => %s, mysqlUrl -> %s, mysqlHost -> %s",redisHost,mysqlUrl,mysqlHost);

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug(info);
        }

        return HttpResult.success(info);
    }

    @PostMapping
    public HttpResult<String> addClientDetails(@RequestBody BaseClientDetails clientDetails){
        clientRegistrationService.addClientDetails(clientDetails);
        return HttpResult.success("创建客户端信息成功...");
    }

    @PutMapping
    public HttpResult<String> updateClientDetails(@RequestBody BaseClientDetails clientDetails){
        clientRegistrationService.updateClientDetails(clientDetails);
        return HttpResult.success("更新客户端信息成功...");
    }

    @PostMapping("/secret")
    public HttpResult<String> updateClientSecret(@RequestParam("id") String clientId, @RequestParam("secret") String secret){
        clientRegistrationService.updateClientSecret(clientId,secret);
        return HttpResult.success("更新客户端密码成功...");
    }

    @DeleteMapping("/{id}")
    public HttpResult<String> removeClientDetails(@PathVariable("id") String clientId){
        clientRegistrationService.removeClientDetails(clientId);
        return HttpResult.success("删除客户端信息成功...");
    }

    @GetMapping
    HttpResult<List<ClientDetails>> listClientDetails(){
        return HttpResult.success(clientRegistrationService.listClientDetails());
    }
}
