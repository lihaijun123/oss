package com.focustech.oss2008.service.impl;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <li>數據緩存</li>
 *
 * @author xufei 2008-4-17 上午11:50:14<a href="mailto:xufei@made-in-china.com">contact Xu Fei</a>
 */
public class ParameterCacheInterceptor implements MethodInterceptor {
    private Cache cache;

    // @Autowired
    // private BaseParametersService parametersService = null;
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public ParameterCacheInterceptor() {
        super();
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        Object result;
        String cacheKey = getCacheKey(arguments);
        Element element = cache.get(cacheKey);
        if (element == null) {
            result = invocation.proceed();
            element = new Element(cacheKey, (Serializable) result);
            cache.put(element);
        }
        return element.getValue();
    }

    public String getCacheKey(Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }
}
