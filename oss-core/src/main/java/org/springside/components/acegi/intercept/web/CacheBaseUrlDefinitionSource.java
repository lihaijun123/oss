package org.springside.components.acegi.intercept.web;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.Perl5Compiler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springside.components.acegi.cache.AcegiCacheManager;
import org.springside.components.acegi.resource.Resource;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.utils.PatternUtils;

/**
 * 在resourceCache中獲取當前調用方法相對應類型為 Url 的Resouce實例 lookupAttributes(String url) 方法被
 * {@link org.acegisecurity.intercept.web.FilterSecurityInterceptor} 調用 useAntPath 是否選用ant path的匹配模式 protectAllResource
 * 是否默認情況下所有的資源都需要受保護 convertUrlToLowercaseBeforeComparison 是否需要把Url轉為小寫後再進行比較
 *
 * @author cac
 */
public class CacheBaseUrlDefinitionSource extends AbstractFilterInvocationDefinitionSource {
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    private boolean convertUrlToLowercaseBeforeComparison = false;
    private boolean useAntPath = true;
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private AcegiCacheManager acegiCacheManager;
    /** 不需要權限檢查的url */
    private List<String> unCheckedUrl;

    public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager) {
        this.acegiCacheManager = acegiCacheManager;
    }

    /**
     * 返回當前URL對應的權限
     *
     * @see org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource#lookupAttributes(java.lang.String)
     */
    @Override
    public ConfigAttributeDefinition lookupAttributes(String url) {
        url = preDealUrl(url, isUseAntPath(), isConvertUrlToLowercaseBeforeComparison());
        List<String> urls = acegiCacheManager.getResourcesByType(Resource.RESOURCE_TYPE_URL);
        // 倒敘排序所有獲取的url
        if (isUncheckUrl(url) || isAdmin()) {
            // 無需檢查的url或者當前用戶為admin
            if (false)
                log.error("is admin,uncheck!");
            return null;
        }
        //add by lihaijun 20120625
        return null;
        // orderUrls(urls);
        /*Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Map<String, OssConfigAttribute> scope = null;
        boolean isMatched = false;
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            String resString = iterator.next();
            // 以首先匹配成功的資源的權限作為當前Url的權限
            if (isResourceMatch(isUseAntPath(), url, resString)) {
                isMatched = true;
                ResourceDetails urlResource = acegiCacheManager.getResourceFromCache(resString);
                CollectionUtils.addAll(authorities, urlResource.getAuthorities());
                scope = urlResource.getScope();
                if (false) {
                    String authoritiesStringLog = "";
                    for (int i = 0; null != urlResource.getAuthorities() && i < urlResource.getAuthorities().length; i++) {
                        authoritiesStringLog += urlResource.getAuthorities()[i].getAuthority() + "~";
                    }
                    String scopeStringLog = "";
                    if (null != scope && null != scope.keySet()) {
                        Iterator<String> scopes = scope.keySet().iterator();
                        for (; scopes.hasNext();) {
                            String key = scopes.next();
                            scopeStringLog += key + ":" + scope.get(key).getScope() + "~";
                        }
                    }
                    log.error("url :" + url + " . match resource :" + resString + ";authorities:"
                            + authoritiesStringLog + ";scope:" + scopeStringLog);
                }
                break;
            }
        }
        // if (!isMatched)
        // {
        // if (url.startsWith("/customer.do?method=accountDetails&customerId="))
        // {
        // log.error("url :" + url + " do not match anything.url size:" + urls.size() + ";url contains:"
        // + urls.contains("^/customer\\.do\\?method=details(.)*"));
        // if (urls.contains("^/customer\\.do\\?method=details(.)*"))
        // {
        // Pattern pattern = PatternUtils.getPattern("^/customer\\.do\\?method=details(.)*");
        // log.error("match pattern:" + (null == pattern ? "null" : pattern.toString()));
        // }
        // else
        // {
        // log.error(urls);
        // }
        // }
        // else
        // log.error("url :" + url + " do not match anything.");
        // }
        return AuthenticationHelper.getCadByAuthorities(authorities, scope, isAuthenticated());
        */
    }

    /**
     * 用戶是否成功登陸
     *
     * @return
     */
    protected boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.isAuthenticated()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 用戶是否成功登陸
     *
     * @return
     */
    protected boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.isAuthenticated() && authentication.getAuthorities() != null
                && authentication.getAuthorities().length > 0) {
            return authentication.getAuthorities()[0].getAuthority().equals(Constants.ROLE_ADMIN);
        }
        else {
            return false;
        }
    }

    /**
     * 把url資源按倒序排序
     *
     * @param urls
     */
    private void orderUrls(List<String> urls) {
        Collections.sort(urls);
        Collections.reverse(urls);
    }

    /**
     * 根據是否使用UseAntPath和是否字符小寫化來預先格式化url
     *
     * @param url
     * @param isUseAntPath
     * @param isToLowercase
     * @return
     */
    private String preDealUrl(String url, boolean isUseAntPath, boolean isToLowercase) {
        if (isUseAntPath) {
            // Strip anything after a question mark symbol, as per SEC-161.
            int firstQuestionMarkIndex = url.lastIndexOf("?");
            if (firstQuestionMarkIndex != -1) {
                url = url.substring(0, firstQuestionMarkIndex);
            }
        }
        if (isToLowercase) {
            url = url.toLowerCase();
        }
        return url;
    }

    /**
     * 查看當前url和資源中的url是否匹配
     *
     * @param isUseAntPath
     * @param runningUrl
     * @param rescUrl
     * @return
     */
    private boolean isResourceMatch(Boolean isUseAntPath, String runningUrl, String rescUrl) {
        if (isUseAntPath) {
            return pathMatcher.match(rescUrl, runningUrl);
        }
        else {
            return PatternUtils.isPerl5Match(rescUrl, runningUrl, Perl5Compiler.READ_ONLY_MASK);
        }
    }

    @SuppressWarnings("unchecked")
    public Iterator getConfigAttributeDefinitions() {
        return null;
    }

    public boolean isUncheckUrl(String url) {
        for (int i = 0; i < unCheckedUrl.size(); i++) {
            String unchecked = unCheckedUrl.get(i);
            if (PatternUtils.isJavaPatternMatch(unchecked, url)) {
                return true;
            }
        }
        return false;
    }

    // ---------getters and setters---------------------
    public void setConvertUrlToLowercaseBeforeComparison(boolean convertUrlToLowercaseBeforeComparison) {
        this.convertUrlToLowercaseBeforeComparison = convertUrlToLowercaseBeforeComparison;
    }

    public boolean isConvertUrlToLowercaseBeforeComparison() {
        return convertUrlToLowercaseBeforeComparison;
    }

    public boolean isUseAntPath() {
        return useAntPath;
    }

    public void setUseAntPath(boolean useAntPath) {
        this.useAntPath = useAntPath;
    }

    public List<String> getUnCheckedUrl() {
        return unCheckedUrl;
    }

    public void setUnCheckedUrl(List<String> unCheckedUrl) {
        this.unCheckedUrl = unCheckedUrl;
    }
}
