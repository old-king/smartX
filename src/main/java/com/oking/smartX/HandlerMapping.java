package com.oking.smartX;

import com.oking.smartX.bean.RequestBean;

import java.util.Map;

/**
 * @author 谢青
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2017/4/19 0019 19:13
 * ${tags}
 */
public class HandlerMapping {
    public static Route getHandle(String requestPath, String requestMethod) {
         Map<RequestBean,Route> handles= ControllerFactory.getRouteMap();

        for (Map.Entry<RequestBean, Route> requestBeanRouteEntry : handles.entrySet()) {
            RequestBean bean=requestBeanRouteEntry.getKey();
            if(bean.getUrl().equals(requestPath)&&bean.getMethod().equals(requestMethod)){
                Route route=requestBeanRouteEntry.getValue();
                if(route!=null){
                    return route;
                }
            }
        }
        return null;
    }
}
