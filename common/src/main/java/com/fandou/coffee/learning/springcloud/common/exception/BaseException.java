package com.fandou.coffee.learning.springcloud.common.exception;

/**
 * 通用异常
 */
public class BaseException extends RuntimeException {

    private ExceptionCode exceptionCode;

    public BaseException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BaseException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }


    public int getCode() {
        return exceptionCode.getCode();
    }

    public String getMsg() {
        return exceptionCode.getMessage();
    }

}
