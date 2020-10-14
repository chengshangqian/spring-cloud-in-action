package com.fandou.coffee.learning.springcloud.user.controller;

import com.fandou.coffee.learning.springcloud.common.result.HttpResult;
import com.fandou.coffee.learning.springcloud.common.model.User;
import com.fandou.coffee.learning.springcloud.user.model.UserBlogDTO;
import com.fandou.coffee.learning.springcloud.user.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

/**
 * 用户服务API
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户服务",produces = "application/json",protocols = "http,https") // 定义用户服务API，默认均返回json格式，支持http,https协议的调用
public class UserController {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 封装了用户对象的HttpResult结果
     */
    @ApiOperation(value = "获取用户详情", notes = "根据用户id获取用户详细信息,需要认证授权后才可访问.")
    @ApiImplicitParams({
        @ApiImplicitParam( name = "id", value = "用户id", required = true)
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回用户详细信息。如果用户不存在，将返回空")
    })
    @GetMapping("/{id}")
    public HttpResult<User> get(@PathVariable("id") Integer id) {
        User user = userService.get(id);
        return HttpResult.success(user);
    }

    /**
     * 用户博客列表
     *
     * @param username 用户账号
     * @return 封装了用户对象user和博客列表blogs的HttpResult结果
     */
    @ApiOperation(value = "用户博客列表", notes = "根据用户账号，查找用户发表的所有博客,需要认证授权后才可访问.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户账号", required = true)
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回用户user和博客列表blogs，如果用户未发表过博客，博客列表blogs为空")
    })
    @PostMapping("/blogs/{username}")
    public HttpResult<UserBlogDTO> getBlogs(@PathVariable("username") String username) {
        UserBlogDTO userBlogDTO = userService.getBlogs(username);
        return HttpResult.success(userBlogDTO);
    }

    /**
     * 获取用户详情：blog模块调用,根据用户账号获取用户详细信息
     *
     * @param username 用户账号
     * @return 封装了用户对象的HttpResult结果
     */
    @ApiOperation(value = "获取用户详情", notes = "根据用户账号，根据用户账号获取用户详细信息,需要认证授权后才可访问.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户账号", required = true)
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回用户详细信息")
    })
    @PostMapping("/{username}")
    public HttpResult<User> getByUsername(@PathVariable("username") String username) {
        User user = userService.getByUsername(username);
        return HttpResult.success(user);
    }

    /**
     * 获取当前认证用户的认证主体信息
     *
     * @param principal 认证主体信息，框架自动注入
     * @return 封装了认证主体principal的HttpResult结果
     */
    @ApiOperation(value = "获取认证详情", notes = "根据认证用户的认证信息，获取用户认证详细信息，包括用户详细信息，需要认证授权后才可访问.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回用户的认证主体信息principal")
    })
    @GetMapping("/current")
    public HttpResult<Principal> get(@ApiIgnore("不需要调用者传入") Principal principal) {
        return HttpResult.success(principal);
    }

    /**
     * 获取用户列表
     *
     * @return 封装了用户列表的HttpResult结果
     */
    @ApiOperation(value = "获取用户列表", notes = "获取当前数据库中的所有用户列表信息，需要认证授权后才可访问.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回用户列表")
    })
    @GetMapping
    public HttpResult<List<User>> list() {
        List<User> users = userService.list();
        return HttpResult.success(users);
    }

    /**
     * 创建一个用户
     *
     * @param user 用户对象
     * @return 操作成功的记录条数
     */
    @ApiOperation(value = "创建新用户", notes = "创建一个新的用户，需要认证授权后才可访问.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回操作成功的记录条数")
    })
    @PostMapping
    public HttpResult<Integer> create(@RequestBody User user) {
        Integer count = userService.create(user);
        return HttpResult.success(count);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return 操作成功的记录条数
     */
    @ApiOperation(value = "更新用户信息", notes = "根据用户id更新用户信息")
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回操作成功的记录条数")
    })
    @PutMapping
    public HttpResult<Integer> update(@RequestBody User user) {
        Integer count = userService.update(user);
        return HttpResult.success(count);
    }

    /**
     * 更新用户密码
     *
     * @param user 用户对象
     * @return 操作成功的记录条数
     */
    @ApiOperation(value = "更新用户密码", notes = "根据用户id更新用户密码,传入的JSON数据只需要id和password字段")
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回操作成功的记录条数")
    })
    @PutMapping("/password")
    public HttpResult<Integer> updatePassword(@RequestBody User user) {
        Integer count = userService.updatePassword(user);
        return HttpResult.success(count);
    }

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     * @return 操作成功的记录条数
     */
    @ApiOperation(value = "删除一个用户", notes = "根据用户id删除一个用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "用户id", required = true)
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功，返回操作成功的记录条数")
    })
    @DeleteMapping("/{id}")
    public HttpResult<Integer> delete(@PathVariable("id") Integer id) {
        Integer count = userService.delete(id);
        return HttpResult.success(count);
    }

    /**
     * Swagger注解示例：@RequestBody参数会自动被扫描
     *
     * @param id 用户id
     * @return 返回封装了用户详细信息的Http结果对象
     */
    // 定义一个接口/方法描述
    @ApiOperation(
            value = "获取用户详情V1(Swagger示例)"  // 接口摘要描述
            , notes = "根据用户id获取用户详细信息,需要认证授权后才可访问." // 接口详细描述
            , httpMethod = "GET" // 接口接收的http请求方法，"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" , "PATCH"
            //,consumes = "application/json" // 接口消费即接受的输入类型（media type），多个可以使用逗号分隔
            , produces = "application/json" // 接口生产即返回类型（media type），多个可以使用逗号分隔
            , protocols = "http,https" // 接口支持的请求协议类型，比如http,https等，多个可以使用逗号隔开
            , hidden = false // API文档中不显示（隐藏）此接口，默认false，相当于对接口使用@ApiIgnore注解
    )
    // 请求参数描述
    @ApiImplicitParams({
            // 请求参数之一：路径参数
            @ApiImplicitParam(
                    name = "id", //  参数名
                    value = "用户id", // 参数描述
                    required = true, // 是否必须
                    paramType = "path", // 参数来源：header(@RequestHeader),query(@RequestParam),path(@PathVariable),body(@RequestBody),form
                    dataTypeClass = Integer.class // 参数类型
            ),
            // 请求参数之二：请求头参数
            @ApiImplicitParam(name = "jwtToken", value = "OAuth2认证的jwt令牌", required = true, paramType = "header", dataTypeClass = String.class),
            // 请求参数之二：请求条件参数
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "query", dataTypeClass = String.class),
            // 请求参数之二：cookie参数，目前不支持显示，默认为query
            @ApiImplicitParam(name = "rmb-me", value = "记住我", required = true, dataTypeClass = String.class
                    //,paramType = "cookie"
            )
    })
    // 响应结果描述集合
    @ApiResponses({
        // 响应结果之一
        @ApiResponse(
            code = 200, // 状态码：http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
            message = "操作成功，返回用户详细信息。如果用户不存在，将返回空", // 响应结果描述
            response = HttpResult.class // 响应结果类型
        )
    })
    @GetMapping("/api/v1/{id}")
    public HttpResult<User> apiDemo(@PathVariable("id") Integer id,
                                     @RequestHeader("jwtToken") String accessToken,
                                     @RequestParam("username") String username,
                                     @CookieValue("rmb-me") String rmb, // 页面不支持显示cookie类型
                                     @RequestBody User user
                                     ) {

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("测试...");
        }

        return HttpResult.success(user);
    }
}
