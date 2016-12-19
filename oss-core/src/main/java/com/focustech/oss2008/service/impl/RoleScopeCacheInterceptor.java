package com.focustech.oss2008.service.impl;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-30 下午02:07:58
 */
public class RoleScopeCacheInterceptor implements MethodInterceptor {
    private Cache cache;

    // @Autowired
    // private BaseParametersService parametersService = null;
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /*
     * (non-Javadoc)
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        String key = getCacheKey(arguments);
        Object result;
        if (cache.isKeyInCache(key)) {
            return cache.get(key).getValue();
        }
        else {
            result = invocation.proceed();
            Element element = new Element(key, result);
            cache.put(element);
            return result;
        }
    }

    /**
     * @param arguments
     * @return
     */
    protected String getCacheKey(Object[] arguments) {
        String result = "";
        for (Object argument : arguments) {
            result += argument.toString() + ".";
        }
        return result;
    }
}
