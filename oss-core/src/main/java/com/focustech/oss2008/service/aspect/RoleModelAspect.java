package com.focustech.oss2008.service.aspect;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

/**
 * <li>角色模塊切面</li>
 *
 * @author yangpeng 2008-10-10 上午11:06:20
 */
@Aspect
public class RoleModelAspect implements Ordered {
    private int order;
    private Cache cache;

    @Around("execution(* com.focustech.oss2008.service.impl.RoleServiceImpl.getModelByRole(String))")
    public Object getRoleModelAspect(ProceedingJoinPoint p) throws Throwable {
        Object[] arguments = p.getArgs();
        Object result;
        Element element = cache.get(arguments[0]);
        if (element == null) {
            result = p.proceed();
            element = new Element(arguments[0], (Serializable) result);
            cache.put(element);
        }
        return element.getValue();
    }

    public void clearCache() {
        cache.removeAll();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    public int getOrder() {
        return order;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
