package org.springside.components.acegi.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.providers.dao.cache.EhCacheBasedUserCache;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.springside.components.acegi.resource.ResourceDetails;

/**
 * AcegiCacheManager是對緩存進行統一管理，以屏蔽其它類對緩存的直接操作 對緩存中的用戶和資源進行初始化、增、刪、改、清空等操作
 *
 * @author cac
 */
public class AcegiCacheManager {
    private UserCache userCache;
    private ResourceCache resourceCache;
    // rescTypeMapp 映射資源類型對應的資源的一對多關系，以便快速查找。
    // 如method類型對應哪些資源實例，url資源類型對應哪些資源實例
    private Map<String, List<String>> rescTypeMapping;

    // -----constructor using fields
    public AcegiCacheManager(UserCache userCache, ResourceCache resourceCache) {
        this.userCache = userCache;
        this.resourceCache = resourceCache;
        // 獲取所有的資源,以初始化 rescTypeMapping
        rescTypeMapping = new HashMap<String, List<String>>();
        List resclist = resourceCache.getAllResources();
        for (Iterator iter = resclist.iterator(); iter.hasNext();) {
            String resString = (String) iter.next();
            ResourceDetails resc = resourceCache.getResourceFromCache(resString);
            List<String> typelist = rescTypeMapping.get(resc.getResType());
            if (typelist == null) {
                typelist = new ArrayList<String>();
                rescTypeMapping.put(resc.getResType(), typelist);
            }
            typelist.add(resString);
        }
        for (Iterator<String> iterator = rescTypeMapping.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            List<String> list = rescTypeMapping.get(key);
            Collections.sort(list);
            Collections.reverse(list);
        }
    }

    // -----get from cache methods
    public UserDetails getUser(String username) {
        return userCache.getUserFromCache(username);
    }

    public ResourceDetails getResourceFromCache(String resString) {
        return resourceCache.getResourceFromCache(resString);
    }

    // -----remove from cache methods
    public void removeUser(String username) {
        userCache.removeUserFromCache(username);
    }

    // public void removeResource(String resString)
    // {
    // ResourceDetails rd = resourceCache.getResourceFromCache(resString);
    // List<String> typelist = rescTypeMapping.get(rd.getResType());
    // typelist.remove(resString);
    // resourceCache.removeResourceFromCache(resString);
    // }
    // ------add to cache methods
    public void addUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities) {
        User user =
                new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                        authorities);
        addUser(user);
    }

    public void addUser(UserDetails user) {
        userCache.putUserInCache(user);
    }

    // public void addResource(
    // String resString,
    // String resType,
    // GrantedAuthority[] authorities,
    // Map<String, OssConfigAttribute> roleScopes)
    // {
    // Resource rd = new Resource(resString, resType, authorities, roleScopes);
    // addResource(rd);
    // }
    // public void addResource(ResourceDetails rd)
    // {
    // List<String> typelist = rescTypeMapping.get(rd.getResType());
    // if (typelist == null)
    // {
    // typelist = new ArrayList<String>();
    // rescTypeMapping.put(rd.getResType(), typelist);
    // }
    // typelist.add(rd.getResString());
    // resourceCache.putResourceInCache(rd);
    // }
    // ------renovate cache methods
    // public void renovateUser(
    // String orgUsername,
    // String username,
    // String password,
    // boolean enabled,
    // boolean accountNonExpired,
    // boolean credentialsNonExpired,
    // boolean accountNonLocked,
    // GrantedAuthority[] authorities)
    // {
    // removeUser(orgUsername);
    // addUser(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    // }
    // public void renovateUser(String orgUsername, UserDetails user)
    // {
    // removeUser(orgUsername);
    // addUser(user);
    // }
    // public void renovateResource(
    // String orgResString,
    // String resString,
    // String resType,
    // GrantedAuthority[] authorities,
    // Map<String, OssConfigAttribute> roleScopes)
    // {
    // removeResource(orgResString);
    // addResource(resString, resType, authorities, roleScopes);
    // }
    // public void renovateResource(String orgResString, ResourceDetails rd)
    // {
    // removeResource(orgResString);
    // addResource(rd);
    // }
    // -------getters and setters-------------------
    public void clearResources() {
        rescTypeMapping = new HashMap<String, List<String>>();
        resourceCache.removeAllResources();
    }

    public void setResourceCache(ResourceCache resourceCache) {
        this.resourceCache = resourceCache;
    }

    public ResourceCache getResourceCache() {
        return resourceCache;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    /**
     * 根據資源類型,在rescTypeMapping職工獲取所有該類型資源的對應的resource string
     *
     * @param resType
     * @return List
     */
    public List<String> getResourcesByType(String resType) {
        return rescTypeMapping.get(resType);
    }

    /**
     * 獲取所有資源的對應的resource string
     *
     * @return
     */
    public List<String> getAllResources() {
        return resourceCache.getAllResources();
    }

    /**
     * 獲取所有用戶實例對應的user name
     *
     * @return
     */
    public List<String> getAllUsers() {
        EhCacheBasedUserCache ehUserCache = (EhCacheBasedUserCache) userCache;
        return ehUserCache.getCache().getKeys();
    }
}
