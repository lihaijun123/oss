package com.focustech.oss2008.service.aspect;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

/**
 * <li>鏈接權限切面</li>
 *
 * @author yangpeng 2008-8-7 下午03:37:07
 */
@Aspect
public class UrlAuthorityAspect implements Ordered {
    private int order;
    private Cache cache;

    @Around("execution(* com.focustech.oss2008.service.impl.RoleServiceImpl.getUrlScope(..))")
    public Object getUrlScopeAspect(ProceedingJoinPoint p) throws Throwable {
        Object[] arguments = p.getArgs();
        Object result;
        String cacheKey = getCacheKey(arguments);
        Element element = cache.get(cacheKey);
        if (element == null) {
            result = p.proceed();
            element = new Element(cacheKey, (Serializable) result);
            cache.put(element);
        }
        return element.getValue();
    }

    public void clearCache() {
        cache.removeAll();
    }

    protected String getCacheKey(Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }
}
