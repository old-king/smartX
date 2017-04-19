package com.oking.smartX;

import com.oking.smartX.exception.SmartXException;
import com.oking.smartX.util.PathUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 路由匹配器
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class RouteMatcher {
    private List<Route> routes;

    public RouteMatcher(List<Route> routes) {
        this.routes = routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Route findRoute(String path) {
        String relativePath = parsePath(path);

        List<Route> matchRoutes = this.routes.stream().filter(route -> matchesPath(route.getPath(), relativePath)).collect(Collectors.toList());

        return matchRoutes.size() > 0 ? matchRoutes.get(0) : null;
    }

    /**
     * 得到相对路径path
     *
     * @param path 请求地址
     * @return 返回相对路径path
     */
    public String parsePath(String path) {
        path = PathUtil.fixPath(path);
        try {
            URI uri = new URI(path);
            return uri.getPath();
        } catch (URISyntaxException e) {
            throw new SmartXException(e);
        }
    }

    private boolean matchesPath(String routePath, String pathToMatch) {
        routePath = routePath.replaceAll(PathUtil.VAR_REGEXP, PathUtil.VAR_REPLACE);
        return pathToMatch.matches("(?i)" + routePath);
    }
}
