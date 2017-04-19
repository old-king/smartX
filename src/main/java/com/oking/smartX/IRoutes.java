package com.oking.smartX;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 路由管理器接口
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public interface IRoutes {

    public void addRoute(Route route);

    public void addRoute(String path, Method method, Class<?> controller);

    public void addRoute(List<Route> routes);

    public void removeRouter(Route route);

    public void removeRouter(List<Route> routes);

}
