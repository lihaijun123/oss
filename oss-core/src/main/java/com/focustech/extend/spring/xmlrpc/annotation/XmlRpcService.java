package com.focustech.extend.spring.xmlrpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * <li>xml rpc service 注解</li>
 * <p>
 * 使用此注解的類將會作為xml rpc的service相應xml rpc服務
 *
 * @author yangpeng 2008-8-1 下午03:37:34
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface XmlRpcService {
    /**
     * value為空則xml rpc service的名稱默認使用sping bean的id。否則使用value
     */
    String value() default "";

    /**
     * 是否使用方法注解
     *
     * @return false:默認服務組件中的所有公共方法都作為xml rpc的服務方法 <br>
     *         true:在服務組件
     */
    boolean useMethodAnnotation() default false;
}
