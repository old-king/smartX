package com.oking.smartX;

import com.oking.smartX.annotation.Controller;
import com.oking.smartX.annotation.RequestMapping;
import com.oking.smartX.annotation.RequestMethod;
import com.oking.smartX.util.ClassUtil;
import com.oking.smartX.util.ParameterNameUtils;
import com.oking.smartX.util.PathUtil;
import com.xiaoleilu.hutool.util.ArrayUtil;

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

    private static Map<String, Map<String, Route>> routeMap = new HashMap<>();

    public static Map<String, Map<String, Route>> getRouteMap() {
        return routeMap;
    }

    public static void init(String packageName) {
        //controller注解标识的class
        List<Class<?>> classList = ClassUtil.getClassListByAnnotation(packageName, Controller.class);
        for (Class<?> controllerClass : classList) {
            Method[] methods = controllerClass.getMethods();
            if (ArrayUtil.isNotEmpty(methods)) {
                for (Method method : methods) {
                    handlerControllerMethod(controllerClass, method);
                }
            }
        }

    }

    private static void handlerControllerMethod(Class<?> controllerClass, Method method) {
        String sufixPath = PATH_SEPARATOR;

        //判断类上是否有url注解
        if (controllerClass.isAnnotationPresent(RequestMapping.class)) {
            sufixPath = PathUtil.fixPath(controllerClass.getAnnotation(RequestMapping.class).value());
        }
        if (method.isAnnotationPresent(RequestMapping.class)) {
            String requestPath = PathUtil.fixPath(method.getAnnotation(RequestMapping.class).value());
            RequestMethod requestMethod = method.getAnnotation(RequestMapping.class).method();

            Route route = new Route(sufixPath + requestPath, method, controllerClass, ParameterNameUtils.getMethodParameterNamesByAsm4(controllerClass, method), method.getParameterTypes());

            initHandle(requestMethod.toString(), route, sufixPath + requestPath);
        }
    }

    private static void initHandle(String requestMethod, Route route, String urlPath) {
        if (routeMap.containsKey(requestMethod)) {
            Map<String, Route> routes = routeMap.get(requestMethod);
            if (routes.containsKey(urlPath)) {
                throw new IllegalArgumentException("Same path pattern ");
            } else {
                routes.put(urlPath, route);
            }
        } else {
            routeMap.put(requestMethod, newRouteMap(urlPath, route));
        }

    }

    private static Map<String, Route> newRouteMap(String urlPath, Route route) {
        Map<String, Route> routeMap = new HashMap<>();

        routeMap.put(PathUtil.fixPath(urlPath), route);
        return routeMap;
    }
}
