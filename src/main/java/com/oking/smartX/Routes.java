package com.oking.smartX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class Routes implements IRoutes {
    private static Logger LOGGER = LoggerFactory.getLogger(Routes.class);

    private List<Route> routes = new ArrayList<Route>();


    @Override
    public void addRoute(Route route) {
        routes.add(route);
    }

    @Override
    public void addRoute(String path, Method method, Class controller) {
        Route route = new Route(path, method, controller);
        addRoute(route);

        LOGGER.info("Add route:{}", path);
    }

    @Override
    public void addRoute(List<Route> routes) {
        routes.addAll(routes);
    }

    @Override
    public void removeRouter(Route route) {
        routes.remove(route);
    }

    @Override
    public void removeRouter(List<Route> routes) {
        routes.remove(routes);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
