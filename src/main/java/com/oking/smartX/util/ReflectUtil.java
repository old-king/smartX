package com.oking.smartX.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 谢青
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2017/4/19 0019 20:06
 * ${tags}
 */
public class ReflectUtil {

    public static Object invokeMethod(Class<?> clazz, Method method,Object[] params){
        method.setAccessible(true);
        try {
            Object instance=clazz.newInstance();
            return method.invoke(instance,params);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
