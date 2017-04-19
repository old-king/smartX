package com.oking.smartX;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class RouteTest {
    public static void main(String[] args) {
        String path="http://localhost:8080/usr/1";

        List<Route> routes=new ArrayList<>();
        Route route=new Route("/usr/1", null,Object.class);
        routes.add(route);
        RouteMatcher routeMatcher=new RouteMatcher(routes);
    }
}
