package com.oking.smartX.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢青
 * @Description: web请求工具类
 * @date 2017/4/19 0019 19:23
 * ${tags}
 */
public class WebUtil {

    public static Map<String, String> getParamsByRequest(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();

//        Enumeration<String>  paramNames=request.getParameterNames();
//
//        while(paramNames.hasMoreElements()){
//            String paramName=paramNames.nextElement();
//
//            String[]  paramValues=request.getParameterValues(paramName);
//            if (paramNames != null) {
//                if(paramValues.length==1){
//                    String paramValue=paramValues[0];
//                }
//            }
//        }
        //TODO 暂时不支持数组  只支持单一参数
        Map<String, String> requestParams = new HashMap<>();
        for (Map.Entry<String, String[]> stringEntry : params.entrySet()) {
            requestParams.put(stringEntry.getKey(), stringEntry.getValue()[0]);
        }
        return requestParams;
    }
}
