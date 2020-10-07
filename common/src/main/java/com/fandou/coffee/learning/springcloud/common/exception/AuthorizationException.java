package com.fandou.coffee.learning.springcloud.common.exception;

/**
 * 登录认证异常
 */
public class AuthorizationException extends BaseException {
    public AuthorizationException(String message){
        super(ExceptionCode.FAIL,message);
    }
}
