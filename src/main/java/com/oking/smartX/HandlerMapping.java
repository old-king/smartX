package com.oking.smartX;

import java.util.Map;

/**
 * @author 谢青
 * @Description: 请求匹配器
 * @date 2017/4/19 0019 19:13
 * ${tags}
 */
public class HandlerMapping {
    public static Route getHandle(String requestPath, String requestMethod) {
        Map<String, Map<String, Route>> handles = ControllerFactory.getRouteMap();

        if (handles.containsKey(requestMethod)) {
            Map<String, Route> routeMap = handles.get(requestMethod);
            if (routeMap.containsKey(requestPath)) {
                return routeMap.get(requestPath);
            }
        }
        return null;
    }
}
