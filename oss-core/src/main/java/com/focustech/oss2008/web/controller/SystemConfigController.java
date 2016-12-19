package com.focustech.oss2008.web.controller;

import net.sf.ehcache.Cache;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.components.acegi.cache.AcegiCacheManager;
import org.springside.components.acegi.cache.AcegiCacheManagerFactoryBean;
import org.springside.components.acegi.intercept.web.CacheBaseUrlDefinitionSource;
import org.springside.components.acegi.resource.Resource;

import com.focustech.aut2008.client.service.ClientRefreshCacheService;
import com.focustech.oss2008.acegi.filter.OssAuthenticationProcessingFilter;
import com.focustech.oss2008.service.MenuTree;
import com.focustech.oss2008.service.aspect.RoleModelAspect;
import com.focustech.oss2008.web.AbstractController;

/**
 * <li>系統配置</li>
 *
 * @author yangpeng 2008-6-20 上午09:58:40
 */
@Controller
@RequestMapping("/system_config.do")
public class SystemConfigController extends AbstractController {
    // private ClientRefreshCacheService ptlRefreshCacheService;
    // private ClientRefreshCacheService ccsRefreshCacheService;
    @Autowired
    @Qualifier("acegiCacheManager")
    private AcegiCacheManagerFactoryBean acegiCacheManagerFactoryBean; // url緩存
    @Autowired
    @Qualifier("filterDefinitionSource")
    private CacheBaseUrlDefinitionSource cacheBaseUrlDefinitionSource;
    @Autowired
    private MenuTree menuTree; // 菜單,需要刷新
    @Autowired
    @Qualifier("parameterCache")
    private Cache parameterCache;// 參數緩存
    @Autowired
    @Qualifier("urlScopeCache")
    private Cache urlScopeCache; // 鏈接範圍緩存
    //@Autowired
    //@Qualifier("sortScopeCache")
    private Cache sortScopeCache; // 產品目錄緩存
    @Autowired
    @Qualifier("roleModelAspect")
    private RoleModelAspect roleModelAspect; // 角色模塊緩存
    @Autowired
    @Qualifier("loginUserCache")
    private Cache loginUserCache; // 登陸用戶緩存

    @RequestMapping(params = "method=refresh_cache", method = RequestMethod.POST)
    public String refreshCache(String cacheType) throws Exception {
        Assert.notNull(cacheType, "請選擇刷新的組件");
        String[] cacheTypes = cacheType.split(",");
        // 門戶系統中的緩存，主要有︰鏈接、菜單、鏈接範圍、產品目錄緩存、角色模塊、登錄用戶、
        String ptlCaches = "";
        // CCS系統中的緩存，主要有︰參數緩存
        String ccsCaches = "";
        for (int i = 0; i < cacheTypes.length; i++) {
            if ("urlCache".equals(cacheTypes[i])) {
                refreshUrlCache();
                ptlCaches += ClientRefreshCacheService.CACHE_TYPE_URL_RESOURCE + ",";
            }
            else if ("menuTree".equals(cacheTypes[i])) {
                menuTree.refresh();
                ptlCaches += ClientRefreshCacheService.CACHE_TYPE_MENU + ",";
            }
            else if ("parameterCache".equals(cacheTypes[i])) {
                parameterCache.removeAll();
                ccsCaches += ClientRefreshCacheService.CACHE_TYPE_PARAM + ",";
            }
            else if ("urlScopeCache".equals(cacheTypes[i])) {
                urlScopeCache.removeAll();
                ptlCaches += ClientRefreshCacheService.CACHE_TYPE_URL_SCOPE + ",";
            }
            else if ("sortScopeCache".equals(cacheTypes[i])) {
                sortScopeCache.removeAll();
                ptlCaches += ClientRefreshCacheService.CACHE_TYPE_CATELOG + ",";
            }
            else if ("roleModelAspect".equals(cacheTypes[i])) {
                roleModelAspect.clearCache();
                ptlCaches += ClientRefreshCacheService.CACHE_TYPE_ROLE_MODEL + ",";
            }
            else if ("loginUserCache".equals(cacheTypes[i])) {
                loginUserCache.removeAll();
                ptlCaches += ClientRefreshCacheService.CACHE_TYPE_USER + ",";
            }
        }
        if (StringUtils.isNotEmpty(ptlCaches)) {
            // try {
            // ptlRefreshCacheService =
            // (ClientRefreshCacheService) ContextLoader.getCurrentWebApplicationContext().getBean(
            // "ptlRefreshCacheService");
            // ptlRefreshCacheService.refreshCaches(ptlCaches);
            // }
            // catch (Exception e) {
            // log.error(e);
            // }
        }
        if (StringUtils.isNotEmpty(ccsCaches)) {
            // try {
            // ccsRefreshCacheService =
            // (ClientRefreshCacheService) ContextLoader.getCurrentWebApplicationContext().getBean(
            // "ccsRefreshCacheService");
            // ccsRefreshCacheService.refreshCaches(ccsCaches);
            // }
            // catch (Exception e) {
            // log.error(e);
            // }
        }
        return "succ_refresh";
    }

    /**
     * 刷新url緩存
     */
    private void refreshUrlCache() throws Exception {
        acegiCacheManagerFactoryBean.refreshCache();
        cacheBaseUrlDefinitionSource.setAcegiCacheManager((AcegiCacheManager) acegiCacheManagerFactoryBean.getObject());
    }

    @RequestMapping(params = "method=refresh_cache", method = RequestMethod.GET)
    public String showRefreshCache(ModelMap model) {
        return "/cache_refresh/refresh";
    }

    @RequestMapping(params = "method=view_cache", method = RequestMethod.GET)
    public String viewCache(ModelMap model) throws Exception {
        model.addAttribute("parameterCache", parameterCache);
        model.addAttribute("urlScopeCache", urlScopeCache);
        model.addAttribute("sortScopeCache", sortScopeCache);
        model.addAttribute("resourceCache", ((AcegiCacheManager) acegiCacheManagerFactoryBean.getObject())
                .getResourceCache().getCache());
        model.addAttribute("resourceCache_act_size", ((AcegiCacheManager) acegiCacheManagerFactoryBean.getObject())
                .getResourcesByType(Resource.RESOURCE_TYPE_URL).size());
        model.addAttribute("loginUserCache", loginUserCache);
        model.addAttribute("roleModelCache", roleModelAspect.getCache());
        return "/cache_refresh/view_cache";
    }

    @SuppressWarnings("static-access")
    @RequestMapping(params = "method=refresh_dyn", method = RequestMethod.GET)
    public String refreshDyn(ModelMap model) {
        OssAuthenticationProcessingFilter f = new OssAuthenticationProcessingFilter();
        model.addAttribute("nowDynServer", f.getACTIVE_AUTH_SERVER());
        return "/cache_refresh/dyn";
    }

    @SuppressWarnings("static-access")
    @RequestMapping(params = "method=refresh_dyn", method = RequestMethod.POST)
    public String refreshDyn(String type, String addr, ModelMap model) {
        String msg = "設置完畢！";
        OssAuthenticationProcessingFilter f = new OssAuthenticationProcessingFilter();
        if ("0".equals(type)) {
            f.setACTIVE_AUTH_SERVER("");
            msg += "設置為重新自動尋找方式。";
        }
        else {
            f.setACTIVE_AUTH_SERVER(addr);
            msg += "設置為指定的認證服務器地址︰" + addr;
        }
        model.addAttribute("nowDynServer", f.getACTIVE_AUTH_SERVER());
        model.addAttribute("msg", msg);
        return "/cache_refresh/dyn";
    }
}
