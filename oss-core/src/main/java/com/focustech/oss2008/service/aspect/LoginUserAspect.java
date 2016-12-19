package com.focustech.oss2008.service.aspect;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import com.focustech.aut2008.client.service.ClientRefreshCacheService;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.MenuTree;

/**
 * <li>登陸用戶緩存</li>
 *
 * @author yangpeng 2008-10-10 下午03:14:37
 */
@Aspect
public class LoginUserAspect extends AbstractServiceSupport implements Ordered {
    private int order;
    private Cache cache;
    @Autowired
    private MenuTree menuTree;
    private ClientRefreshCacheService clientRefreshCacheService;

    @Around("execution(* com.focustech.oss2008.service.impl.UserServiceImpl.loadUserByUsername(String))")
    public Object getLoginUserAspect(ProceedingJoinPoint p) throws Throwable {
        Object[] arguments = p.getArgs();
        Object result;
        Element element = cache.get(arguments[0]);
        if (element == null) {
            result = p.proceed();
            return result;
            // element = new Element(arguments[0], (Serializable) result);
            // cache.put(element);
        }
        else {
            return element.getValue();
        }
    }

    @After("execution(* com.focustech.oss2008.service.impl.UserServiceImpl.modify*(com.focustech.oss2008.model.OssAdminUser)) && args (user)")
    public void afterModifyLoginUserAspect(OssAdminUser user) throws Throwable {
        cache.remove(user.getLoginName());
        menuTree.refreshOne(user.getUserId());
        // try {
        // clientRefreshCacheService =
        // (ClientRefreshCacheService) ContextLoader.getCurrentWebApplicationContext().getBean(
        // "ptlRefreshCacheService");
        // clientRefreshCacheService.refreshAfterModifyUser(user.getLoginName(), user.getUserId());
        // }
        // catch (Exception e) {
        // log.error(e);
        // }
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
