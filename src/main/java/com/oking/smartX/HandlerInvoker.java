package com.oking.smartX;

import com.oking.smartX.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 谢青
 * @Description: 请求处理器
 * @date 2017/4/19 0019 19:21
 * ${tags}
 */
public class HandlerInvoker {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerInvoker.class);


    public static void invokeHandler(HttpServletRequest request, HttpServletResponse response, Route route) {
        Object methodInvokeResult;
        String[] paramNames = route.getMethodArgNames();
        if (paramNames != null && paramNames.length > 0) {
            Object[] args = new Object[paramNames.length];
            for (int i = 0; i < paramNames.length; i++) {
                args[i] = castArg(request.getParameter(paramNames[i]), route.getMethodArgTypes()[i]);
            }
            methodInvokeResult = ReflectUtil.invokeMethod(route.getController(), route.getMethod(), args);
        } else {
            methodInvokeResult = ReflectUtil.invokeMethod(route.getController(), route.getMethod(), null);
        }
        LOGGER.info(methodInvokeResult.toString());
    }

    private static <T> T castArg(Object value, Class<T> type) {
        if (value == null) {
            return null;
        }
        if (type.equals(String.class)) {
            value = String.valueOf(value);
        } else if (type.equals(int.class) || type.equals(Integer.class)) {
            value = Integer.parseInt(String.valueOf(value));
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            value = Double.parseDouble(String.valueOf(value));
        } else if (type.equals(long.class) || type.equals(Long.class)) {
            value = Long.parseLong(String.valueOf(value));
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            value = Boolean.parseBoolean(String.valueOf(value));
        }
        return (T) value;
    }
}
