package com.focustech.oss2008.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <li>元數據的顯示名稱</li>
 *
 * @author yangpeng 2008-4-23 下午04:27:00
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Display {
    /**
     * 元數據顯示名稱，默認為空字符串
     */
    String value() default "";
}
