package com.focustech.oss2008.aspact;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <li></li>
 *
 */
public class LogAspect {
    public Object AutoLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Enter");
        String result = (String) pjp.proceed();
        System.out.println("Exit");
        return result;
    }
}
