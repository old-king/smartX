package com.oking.smartX.bean;

import com.oking.smartX.annotation.RequestMethod;

/**
 * @author 谢青
 * @Description: 请求实体类
 * @date 2017/4/19 0019 17:25
 * ${tags}
 */

public class RequestBean {
    private String url;
    private RequestMethod method;

    public RequestBean(String url, RequestMethod requestMethod) {
        this.url=url;
        this.method=requestMethod;
    }

    public RequestBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }
}
