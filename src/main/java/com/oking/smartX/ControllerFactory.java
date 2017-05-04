package com.oking.smartX;

import com.oking.smartX.annotation.Controller;
import com.oking.smartX.annotation.RequestMapping;
import com.oking.smartX.annotation.RequestMethod;
import com.oking.smartX.bean.RequestBean;
import com.oking.smartX.util.ClassUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 谢青
 * @Description: 控制层工厂类
 * @date 2017/4/19 0019 17:19
 * ${tags}
 */
public class ControllerFactory {
    /**
     * 地址分隔符:/
     */
    public static final String PATH_SEPARATOR = "/";

    private static Map<RequestBean, Route> routeMap = new HashMap<>();

    public static Map<RequestBean, Route> getRouteMap() {
        return routeMap;
    }

    public static void setRouteMap(Map<RequestBean, Route> routeMap) {
        ControllerFactory.routeMap = routeMap;
    }

    public static void init(String packageName) {
        //controller注解标识的class
        List<Class<?>> classList = ClassUtil.getClassListByAnnotation(packageName, Controller.class);
        for (Class<?> controllerClass : classList) {
            Method[] methods = controllerClass.getMethods();
            if (ArrayUtils.isNotEmpty(methods)) {
                for (Method method : methods) {
                    handlerControllerMethod(controllerClass, method);
                }
            }
        }

    }

    private static void handlerControllerMethod(Class<?> controllerClass, Method method) {
        String sufixPath = PATH_SEPARATOR;
        if (controllerClass.isAnnotationPresent(RequestMapping.class)) {
            sufixPath = controllerClass.getAnnotation(RequestMapping.class).value() + PATH_SEPARATOR;
        }
        if (method.isAnnotationPresent(RequestMapping.class)) {
            String requestPath = method.getAnnotation(RequestMapping.class).value();
            RequestMethod requestMethod = method.getAnnotation(RequestMapping.class).method();

            routeMap.put(new RequestBean(sufixPath + requestPath, requestMethod), new Route(sufixPath + requestPath, method, controllerClass));
        }
    }
}
