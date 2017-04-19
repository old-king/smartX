package com.oking.smartX.util;

/**
 * 请求地址工具类
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class PathUtil {
    public static final String VAR_REGEXP = ":(\\w+)";
    public static final String VAR_REPLACE = "([^#/?.]+)";
    /**
     * 得到正确请求地址
     */
    public static String fixPath(String path) {
        if (path == null) {
            return "/";
        }
        if (!path.startsWith("/")) {
            path += "/";
        }
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        path = path.replaceAll("\\s", "%20");
        path = path.replaceAll("/{2}", "/");
        return path;
    }
}
