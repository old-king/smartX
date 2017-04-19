package com.oking.smartX;

import com.oking.smartX.annotation.RequestMethod;
import com.oking.smartX.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author 谢青
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2017/4/19 0019 19:21
 * ${tags}
 */
public class HandlerInvoker {
    //TODO
    public static void invokeHandler(HttpServletRequest request, HttpServletResponse response, Route route) {
         Map<String,String> params= WebUtil.getParamsByRequest(request);

       if(request.getMethod().equals(RequestMethod.POST.toString())){
           for (Class<?> param : route.getMethod().getParameterTypes()) {
                String paramName=param.getName();
           }
       }

//        ReflectUtil.invokeMethod(route.getController(),route.getMethod(),)
    }


    public static void main(String[] args) {
        Method[] methods=HandlerInvoker.class.getMethods();
        for (Method method : methods) {
            Class[] parameterTypes=method.getParameterTypes();
//            for (Class parameterType : parameterTypes) {
//                System.out.println(parameterType.getName());
//            }
            Parameter[] params=method.getParameters();
            for (Parameter parameter : params) {
                System.out.println(parameter.getName());
            }
        }
    }
}
