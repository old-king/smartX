package com.oking.smartX;

import java.lang.reflect.Method;

/**
 * 路由
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class Route {
    //方法路由路径
    private String path;
    //完整路由路径
    private String fullPath;

    //执行方法
    private Method method;

    //路由所在控制器
    private Class controller;

    //方法参数名称
    private String[] methodArgNames;
    //方法参数类型
    private Class[] methodArgTypes;

    public Route(String path, Method method, Class<?> controller, String[] methodArgNames, Class[] methodArgTypes) {
        this.path = path;
        this.method = method;
        this.controller = controller;
        this.methodArgNames = methodArgNames;
        this.methodArgTypes = methodArgTypes;
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

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String[] getMethodArgNames() {
        return methodArgNames;
    }

    public void setMethodArgNames(String[] methodArgNames) {
        this.methodArgNames = methodArgNames;
    }

    public Class[] getMethodArgTypes() {
        return methodArgTypes;
    }

    public void setMethodArgTypes(Class[] methodArgTypes) {
        this.methodArgTypes = methodArgTypes;
    }
}
