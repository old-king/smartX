package com.oking.smartX;

import java.lang.reflect.Method;

/**
 * 路由
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class Route {
    //路由路径
    private String path;

    //执行方法
    private Method method;

    //路由所在控制器
    private Class controller;

    public Route() {
    }

    public Route(String path, Method method, Class controller) {
        this.path = path;
        this.method = method;
        this.controller = controller;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getController() {
        return controller;
    }

    public void setController(Class controller) {
        this.controller = controller;
    }
}
