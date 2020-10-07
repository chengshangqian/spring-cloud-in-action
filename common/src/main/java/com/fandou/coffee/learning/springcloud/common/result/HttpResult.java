package com.fandou.coffee.learning.springcloud.common.result;

import java.io.Serializable;

/**
 * Http请求响应结果
 *
 * @param <T> 结果数据对象
 */
public class HttpResult<T> implements Serializable {
    // 序列化id
    private static final long serialVersionUID = -1000005L;

    // 状态编码
    public int code = 0;

    // 响应数据
    public T data;

    // 异常/错误信息
    public String message = "";

    public static <T> HttpResult<T> success(T data) {
        return complete(200,data,null);
    }

    public static <T> HttpResult<T> failure(int code,String message) {
        return complete(code,null,message);
    }

    public static <T> HttpResult<T> error(String message) {
        return complete(500,null,message);
    }

    public static <T> HttpResult<T> complete(int code, T data, String message) {
        HttpResult<T> httpResult = new HttpResult<T>();

        httpResult.code = code;
        if (null != data) {
            httpResult.data = data;
        }
        if (null != message) {
            httpResult.message = message;
        }

        return httpResult;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
