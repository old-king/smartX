package com.oking.smartX.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 谢青
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2017/4/19 0019 17:58
 * ${tags}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "/";
}
