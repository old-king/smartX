package com.oking.smartX.util;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 谢青
 * @Description: 获取参数名称测试工具类
 * @date 2017/5/4 0004 15:30
 * ${tags}
 */
public class ParameterNameUtilsTest {

    public static void main(String[] args) throws Exception {
        Class<ParameterNameUtilsTest> clazz = ParameterNameUtilsTest.class;
        Method method = clazz.getDeclaredMethod("method1", String.class, String.class);
        String[] parameterNames = ParameterNameUtils.getMethodParameterNamesByAsm4(clazz, method);
        System.out.println(Arrays.toString(parameterNames));
    }

    public void method1(String param1, String param2) {
        System.out.println(param1 + param2);
    }

}