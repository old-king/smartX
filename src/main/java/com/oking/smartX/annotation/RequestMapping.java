package com.oking.smartX.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 谢青
 * @Description: 视图层注解
 * @date 2017/4/19 0019 18:00
 * ${tags}
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "/";

    String[] values() default {"/"};

    RequestMethod method() default RequestMethod.GET;
}
