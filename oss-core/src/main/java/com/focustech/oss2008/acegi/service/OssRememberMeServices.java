package com.focustech.oss2008.acegi.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.DisabledException;
import org.acegisecurity.providers.rememberme.RememberMeAuthenticationToken;
import org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.acegi.filter.OssAuthenticationProcessingFilter;
import com.focustech.oss2008.model.OssAdminLoginControl;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.utils.PatternUtils;

/**
 * <li>OSS Remember me service</li>
 * <p>
 * 主要用于在成功登陸後,除了記錄cookie以外,還將用戶密碼臨時記錄在後台數據庫中.當用戶通過cookie登陸時,使用此密碼做驗證,而不使用ldap驗證
 * </p>
 *
 * @author yangpeng 2008-4-14 上午10:18:45
 */
public class OssRememberMeServices extends TokenBasedRememberMeServices {
    private String domain;
    private List<String> unCheckedUrl;
    @Autowired
    private RoleService roleService;
    @Autowired
    EnvironmentParameter environmentParameter;
    @Autowired
    OssAdminParameterService paramService;

    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if ((cookies == null) || (cookies.length == 0)) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (getCookieName().equals(cookies[i].getName())) {
                String cookieValue = cookies[i].getValue();
                for (int j = 0; j < cookieValue.length() % 4; j++) {
                    cookieValue = cookieValue + "=";
                }
                if (Base64.isArrayByteBase64(cookieValue.getBytes())) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Remember-me cookie detected");
                    }
                    // Decode token from Base64
                    // format of token is:
                    // username + ":" + expiryTime + ":" +
                    // Md5Hex(username + ":" + expiryTime + ":" + password + ":"
                    // + key)
                    String cookieAsPlainText = new String(Base64.decodeBase64(cookieValue.getBytes()));
                    String[] cookieTokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, ":");
                    if (cookieTokens.length == 3) {
                        long tokenExpiryTime;
                        try {
                            tokenExpiryTime = new Long(cookieTokens[1]).longValue();
                        }
                        catch (NumberFormatException nfe) {
                            cancelCookie(request, response,
                                    "Cookie token[1] did not contain a valid number (contained '" + cookieTokens[1]
                                            + "')");
                            return null;
                        }
                        if (isTokenExpired(tokenExpiryTime)) {
                            cancelCookie(request, response, "Cookie token[1] has expired (expired on '"
                                    + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
                            return null;
                        }
                        // Check the user exists
                        // Defer lookup until after expiry time checked, to
                        // possibly avoid expensive lookup
                        UserDetails userDetails = loadUserDetails(request, response, cookieTokens);
                        if (userDetails == null) {
                            cancelCookie(request, response, "Cookie token[0] contained username '" + cookieTokens[0]
                                    + "' but was not found");
                            return null;
                        }
                        if (!isValidUserDetails(request, response, userDetails, cookieTokens)) {
                            return null;
                        }
                        // Check signature of token matches remaining details
                        // Must do this after user lookup, as we need the
                        // DAO-derived password
                        // If efficiency was a major issue, just add in a
                        // UserCache implementation,
                        // but recall this method is usually only called one per
                        // HttpSession
                        // (as if the token is valid, it will cause
                        // SecurityContextHolder population, whilst
                        // if invalid, will cause the cookie to be cancelled)
                        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, userDetails);
                        if (!expectedTokenSignature.equals(cookieTokens[2])) {
                            cancelCookie(request, response, "Cookie token[2] contained signature '" + cookieTokens[2]
                                    + "' but expected '" + expectedTokenSignature + "'");
                            return null;
                        }
                        // By this stage we have a valid token
                        if (logger.isDebugEnabled()) {
                            logger.debug("Remember-me cookie accepted");
                        }
                        RememberMeAuthenticationToken auth =
                                new RememberMeAuthenticationToken(getKey(), userDetails, userDetails.getAuthorities());
                        auth.setDetails(authenticationDetailsSource.buildDetails(request));
                        super.loginSuccess(request, response, auth);// 每次重寫cookie
                        return auth;
                    }
                    else {
                        cancelCookie(request, response, "Cookie token did not contain 3 tokens; decoded value was '"
                                + cookieAsPlainText + "'");
                        return null;
                    }
                }
                else {
                    cancelCookie(request, response, "Cookie token was not Base64 encoded; value was '" + cookieValue
                            + "'");
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    protected Cookie makeValidCookie(String tokenValueBase64, HttpServletRequest request, long maxAge) {
        Cookie cookie = new Cookie(getCookieName(), tokenValueBase64);
        cookie.setMaxAge(new Long(maxAge).intValue());
        cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
        cookie.setDomain(domain);
        return cookie;
    }

    @Override
    protected Cookie makeCancelCookie(HttpServletRequest request) {
        Cookie cookie = new Cookie(getCookieName(), null);
        cookie.setMaxAge(0);
        cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
        cookie.setDomain(domain);
        return cookie;
    }

    @Override
    protected void cancelCookie(HttpServletRequest request, HttpServletResponse response, String reasonForLog) {
        logger.error("Cancelling cookie for reason: " + reasonForLog);
        response.addCookie(makeCancelCookie(request));
    }

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices#isTokenExpired(long)
     */
    @Override
    protected boolean isTokenExpired(long tokenExpiryTime) {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices#isValidUserDetails(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse, org.acegisecurity.userdetails.UserDetails, java.lang.String[])
     */
    @Override
    protected boolean isValidUserDetails(HttpServletRequest request, HttpServletResponse response,
            UserDetails userDetails, String[] cookieTokens) {
        OssAdminUser user = (OssAdminUser) userDetails;
        // 判斷該用戶是否可以登陸當前系統
        List<String> models = roleService.getModelListByRole((user).getUserFirstRole());
        // 是否要檢查當前用戶登錄的IP域，內網、外網檢查
        checkUserIp(request, user.getUserFirstRole());
        //
        String sourceCode =
                request.getParameter(OssAuthenticationProcessingFilter.SOURCE_CODE) == null ? "SAL" : request
                        .getParameter(OssAuthenticationProcessingFilter.SOURCE_CODE).toString();
        String uri = request.getRequestURI() + "?" + request.getQueryString();
        if (!models.contains(sourceCode) && !isUncheckedUrl(uri)) {
            DisabledException exception =
                    new DisabledException(MessageUtils.getExceptionValue("USER_LOGIN_DISABLED_EXCEPTION"));
            request.getSession().setAttribute("ACEGI_SECURITY_LAST_EXCEPTION", exception);
            throw exception;
        }
        //
        boolean useSso = environmentParameter.getBooleanValue("USE_SSO");
        if (useSso) {
            OssAdminLoginControl loginControl = user.getLoginControl();
            if (loginControl != null && !request.getRemoteAddr().equals(loginControl.getLoginIp())) {
                cancelCookie(request, response, "has another login.");
                String errMsg = MessageUtils.getExceptionValue("USER_LOGIN_DUPLICATION_EXCEPTION");
                errMsg = errMsg.replaceFirst("%k", loginControl.getLoginIp());
                errMsg = errMsg.replaceFirst("%k", request.getRemoteAddr());
                AccessDeniedException exception = new AccessDeniedException(errMsg);
                throw exception;
            }
        }
        //
        return super.isValidUserDetails(request, response, userDetails, cookieTokens);
    }

    /**
     * 檢查當前登錄用戶使用的IP段是否在合法的段內
     *
     * <pre>
     *  1、當前系統環境是否指定要檢查ip段
     *  2、當前用戶的角色是否要檢查ip段
     * </pre>
     */
    private void checkUserIp(HttpServletRequest request, long userRoleId) {
        boolean checkIp = environmentParameter.getBooleanValue("CHECK_IP");
        if (checkIp == false) {
            return;
        }
        // 看當前用戶是否要做這個檢查
        checkIp = false;
        OssAdminParameter parameter =
                paramService.selectParameterByTypeKeyAndSite(OssAdminParameterService.PARAMETER_TYPE_IP_RANGE, "ROLES",
                        Constants.SITE_TYPE_MIC_CN);
        String roleStr = "";
        if (parameter != null) {
            roleStr = parameter.getParameterValue();
        }
        String[] roles = roleStr.split(",");
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].equals(String.valueOf(userRoleId))) {
                checkIp = true;
                break;
            }
        }
        //
        if (checkIp == false) {
            return;
        }
        parameter =
                paramService.selectParameterByTypeKeyAndSite(OssAdminParameterService.PARAMETER_TYPE_IP_RANGE,
                        "IP_RANGE", Constants.SITE_TYPE_MIC_CN);
        String ipStr = "";
        if (parameter != null) {
            ipStr = parameter.getParameterValue();
        }
        String clientIp = request.getRemoteAddr();
        String[] ips = ipStr.split(",");
        boolean inner = false;
        for (int i = 0; i < ips.length; i++) {
            if (clientIp.indexOf(ips[i]) >= 0) {
                inner = true;
                break;
            }
        }
        if (inner == false) {
            DisabledException exception =
                    new DisabledException(MessageUtils.getExceptionValue("USER_LOGIN_DISABLED_EXCEPTION"));
            request.getSession().setAttribute("ACEGI_SECURITY_LAST_EXCEPTION", exception);
            throw exception;
        }
    }

    protected boolean isUncheckedUrl(String url) {
        for (int i = 0; i < unCheckedUrl.size(); i++) {
            String unchecked = unCheckedUrl.get(i);
            if (PatternUtils.isJavaPatternMatch(unchecked, url)) {
                return true;
            }
        }
        return false;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<String> getUnCheckedUrl() {
        return unCheckedUrl;
    }

    public void setUnCheckedUrl(List<String> unCheckedUrl) {
        this.unCheckedUrl = unCheckedUrl;
    }
    // TODO:處理LDAP驗證
}
