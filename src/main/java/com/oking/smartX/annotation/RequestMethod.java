package com.oking.smartX.annotation;

/**
 * @author 谢青
 * @Description: 请求方法枚举
 * @date 2017/4/19 0019 18:04
 * ${tags}
 */
public enum RequestMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private RequestMethod() {
    }
}
