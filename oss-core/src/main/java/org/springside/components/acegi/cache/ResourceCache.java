package org.springside.components.acegi.cache;

import java.util.List;

import net.sf.ehcache.Cache;

import org.springside.components.acegi.resource.ResourceDetails;

public interface ResourceCache {
    public ResourceDetails getResourceFromCache(String resString);

    public void putResourceInCache(ResourceDetails resourceDetails);

    public void removeResourceFromCache(String resString);

    public List getAllResources();

    public void removeAllResources();

    public void setCache(Cache cache);

    public Cache getCache();
}
