package com.focustech.oss2008.acegi.vote;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.intercept.web.FilterInvocation;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.Perl5Compiler;
import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.acegi.OssConfigAttribute;
import com.focustech.oss2008.dao.RoleResourceDao;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.model.RoleResource;
import com.focustech.oss2008.service.impl.ScopeAuthorityHelper;
import com.focustech.oss2008.utils.PatternUtils;

/**
 * <li>範圍權限投票器</li>
 *
 * @author yangpeng 2008-7-1 下午05:20:04
 */
public class ScopeVoter implements AccessDecisionVoter {
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    private List<String> noCheckUrlPerl5Pattern;
    @Autowired
    private RoleResourceDao<RoleResource> roleResourceDao;

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.vote.AccessDecisionVoter#supports(org.acegisecurity.ConfigAttribute)
     */
    public boolean supports(ConfigAttribute attribute) {
        if (attribute != null && attribute instanceof OssConfigAttribute)
            return true;
        else
            return false;
    }

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.vote.AccessDecisionVoter#supports(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.vote.AccessDecisionVoter#vote(org.acegisecurity.Authentication, java.lang.Object,
     * org.acegisecurity.ConfigAttributeDefinition)
     */
    @SuppressWarnings("unchecked")
    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        OssConfigAttribute ossConfigAttribute = null;
        String currentUserAuthority = null;// 當前用戶角色
        String currentUserId = null;// 用戶編號
        boolean hasAuthority = false; // 是否配置了權限約束
        boolean currentAuthorityHasScope = false;// 當前用戶是否擁有此資源的訪問權限
        if (authentication.getAuthorities() == null || authentication.getAuthorities().length == 0
                || StringUtils.isEmpty(authentication.getAuthorities()[0].getAuthority()))
            // 未登陸,拒絕訪問
            return ACCESS_DENIED;
        else {
            if (isNotCheckUrl(object)) {
                // 當前鏈接無需範圍權限檢查
                return ACCESS_GRANTED;
            }
            currentUserAuthority = authentication.getAuthorities()[0].getAuthority();// 默認取角色列表中的第一個角色
            currentUserId = ((OssAdminUser) authentication.getPrincipal()).getUserId();
            Iterator<OssConfigAttribute> iterator = (Iterator<OssConfigAttribute>) config.getConfigAttributes();
            for (; iterator.hasNext();) {
                hasAuthority = true;
                ossConfigAttribute = iterator.next();
                if (ossConfigAttribute.getAttributeType() == OssConfigAttribute.ATTRIBUTE_TYPE_ROLE) {
                    if (currentUserAuthority.equals(ossConfigAttribute.getAttribute())) {
                        currentAuthorityHasScope = true;
                        break;
                    }
                }
                else {
                    if (currentUserId.equals(ossConfigAttribute.getAttribute())) {
                        currentAuthorityHasScope = true;
                        break;
                    }
                }
            }
            if (!hasAuthority) {
                // 未配置資源權限的，默認所有的角色都有訪問資源的權限
                if (false)
                    log.error("everyone can visit this url");
                return ACCESS_GRANTED;
            }
            else if (currentAuthorityHasScope) {
                // 擁有範圍權限,進行驗證
                if (ossConfigAttribute.hasFullAuthory()) {
                    // 擁有完全控制權限
                    if (false)
                        log.error("user :" + currentUserId + " has full auth. user auth:" + currentUserAuthority);
                    return ACCESS_GRANTED;
                }
                else if (ossConfigAttribute.isSqlConfig()) {
                    // sql範圍配置
                    String sql = ScopeAuthorityHelper.analysisSql(ossConfigAttribute.getScope());
                    Map<String, Object> parameters =
                            getParameterFromRequest(authentication, ossConfigAttribute.getParameterNames(),
                                    (FilterInvocation) object);
                    int result = roleResourceDao.selectScopeAuthority(sql, parameters);
                    if (result > 0) {
                        if (false)
                            log.error("user :" + currentUserId + " has auth for this url. scope sql is :" + sql
                                    + ". user auth :" + currentUserAuthority);
                        return ACCESS_GRANTED;
                    }
                    else {
                        if (false)
                            log.error("user :" + currentUserId + " has no auth for this url. scope sql is :" + sql
                                    + ". user auth :" + currentUserAuthority);
                        return ACCESS_DENIED;
                    }
                }
                else if (ossConfigAttribute.isJavaConfig()) {
                    // 暫不處理java配置
                    return ACCESS_DENIED;
                }
                else {
                    // 錯誤配置,不允許請求
                    return ACCESS_DENIED;
                }
            }
            else
                // 當前角色沒有訪問此資源的角色
                return ACCESS_DENIED;
        }
    }

    /**
     * 判斷當前鏈接是否需要範圍檢查
     *
     * @param object FilterInvocation,包含當前請求鏈接
     */
    protected boolean isNotCheckUrl(Object object) {
        if (object instanceof FilterInvocation) {
            String url = ((FilterInvocation) object).getRequestUrl();
            for (String urlPattern : noCheckUrlPerl5Pattern) {
                if (PatternUtils.isPerl5Match(urlPattern, url, Perl5Compiler.READ_ONLY_MASK)) {
                    if (false)
                        log.error("check url is :" + url + " ,that is unchecked url.");
                    return true;
                }
            }
            if (false)
                log.error("check url is :" + url + " ,that is checked url.");
            return false;
        }
        return false;
    }

    protected Map<String, Object> getParameterFromRequest(Authentication authentication, Set<String> parameterNames,
            FilterInvocation filterInvocation) {
        ServletRequest request = filterInvocation.getRequest();
        Map<String, Object> parameters = new HashMap<String, Object>();
        for (String parameterName : parameterNames) {
            parameters.put(parameterName, request.getParameter(parameterName));
        }
        // 構造公共參數信息,如當前登陸人員的編號、姓名等等
        OssAdminUser user = (OssAdminUser) authentication.getPrincipal();
        parameters.put("userId", user.getUserId());
        parameters.put("departmentId", user.getOssAdminDepartment().getDepartmentId());
        parameters.put("authority", authentication.getAuthorities() != null
                && authentication.getAuthorities().length > 0 ? authentication.getAuthorities()[0].getAuthority() : "");
        return parameters;
    }

    public List<String> getNoCheckUrlPerl5Pattern() {
        return noCheckUrlPerl5Pattern;
    }

    public void setNoCheckUrlPerl5Pattern(List<String> noCheckUrlPerl5Pattern) {
        this.noCheckUrlPerl5Pattern = noCheckUrlPerl5Pattern;
    }
}
