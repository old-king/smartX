package com.oking.smartX;

import com.oking.smartX.util.ClassUtil;

import java.util.List;

/**
 * @author 谢青
 * @Description: Class工具类测试类
 * @date 2017/4/19 0019 16:47
 * ${tags}
 */
public class ClassUtilTest {

    public static void main(String[] args) {
        String packageName = "com.oking.smartX";
        List classes = ClassUtil.getClassList(packageName);
        classes.forEach(clz -> System.out.println(clz.getClass().getName()));
    }
}
