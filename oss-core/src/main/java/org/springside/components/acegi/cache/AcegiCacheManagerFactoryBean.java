package org.springside.components.acegi.cache;

import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;

import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.providers.dao.cache.NullUserCache;
import org.acegisecurity.userdetails.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springside.components.acegi.resource.Resource;
import org.springside.components.acegi.service.AuthenticationService;

/**
 * AcegiCacheManagerFactoryBean 負責初始化緩存後生成AcegiCacheManager 調用 authenticationService 來獲取資源和用戶實例，並加入UserCache 和
 * ResourceCache 中
 *
 * @author cac
 */
public class AcegiCacheManagerFactoryBean implements FactoryBean, InitializingBean {
    protected final Log logger = LogFactory.getLog(getClass());
    private AcegiCacheManager acegiCacheManager;
    private AuthenticationService authenticationService;
    private Cache cloneResourceCache;
    // -------resource caches---------------------
    private UserCache userCache = new NullUserCache();
    private ResourceCache resourceCache;

    public Object getObject() throws Exception {
        return this.acegiCacheManager;
    }

    public Class getObjectType() {
        return (this.acegiCacheManager != null ? this.acegiCacheManager.getClass() : AcegiCacheManager.class);
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        logger.info("Initializing AcegiCacheManager");
        Assert.notNull(userCache, "userCache should not be null");
        Assert.notNull(resourceCache, "resourceCache should not be null");
        Assert.notNull(authenticationService, "Authentication Service should not be null");
        Assert.notNull(cloneResourceCache, "resourceCache must has a copy");
        // 初始化緩存
        if (!(userCache instanceof NullUserCache)) {
            List<User> users = authenticationService.getUsers();
            for (Iterator iter = users.iterator(); iter.hasNext();) {
                User user = (User) iter.next();
                userCache.putUserInCache(user);
            }
        }
        List<Resource> rescs = authenticationService.getResources();
        resourceCache.removeAllResources();
        for (Iterator iter = rescs.iterator(); iter.hasNext();) {
            Resource resc = (Resource) iter.next();
            resourceCache.putResourceInCache(resc);
        }
        // 生成 acegiCacheManager
        this.acegiCacheManager = new AcegiCacheManager(userCache, resourceCache);
    }

    public void refreshCache() throws Exception {
        List<Resource> rescs = authenticationService.getResources();
        // 克隆cache，臨時使用一下
        Cache oriCache = resourceCache.getCache();
        List keys = oriCache.getKeys();
        cloneResourceCache.removeAll();
        for (int i = 0; null != keys && i < keys.size(); i++) {
            Object key = keys.get(i);
            cloneResourceCache.put(oriCache.get(key));
        }
        resourceCache.setCache(cloneResourceCache);//
        oriCache.removeAll();
        oriCache.getStatistics().clearStatistics();// 刷新緩存後，清除所有統計
        ResourceCache newResourceCache = new EhCacheBasedResourceCache();
        newResourceCache.setCache(oriCache);
        //
        for (Iterator iter = rescs.iterator(); iter.hasNext();) {
            Resource resc = (Resource) iter.next();
            newResourceCache.putResourceInCache(resc);
        }
        resourceCache = newResourceCache;
        //
        this.acegiCacheManager = new AcegiCacheManager(userCache, resourceCache);
    }

    // -------------setters-----------
    public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager) {
        this.acegiCacheManager = acegiCacheManager;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    public void setResourceCache(ResourceCache resourceCache) {
        this.resourceCache = resourceCache;
    }

    public Cache getCloneResourceCache() {
        return cloneResourceCache;
    }

    public void setCloneResourceCache(Cache cloneResourceCache) {
        this.cloneResourceCache = cloneResourceCache;
    }
}
