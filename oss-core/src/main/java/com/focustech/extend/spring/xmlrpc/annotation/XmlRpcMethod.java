package com.focustech.extend.spring.xmlrpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <li>標注此方法會作為xmlrpc server的響應方法</li>
 *
 * @author yangpeng 2008-8-1 下午03:33:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XmlRpcMethod {
    String value() default "";
}
