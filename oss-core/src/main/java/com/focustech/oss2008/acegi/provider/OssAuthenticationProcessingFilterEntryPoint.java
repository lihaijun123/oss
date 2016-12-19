package com.focustech.oss2008.acegi.provider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.rememberme.RememberMeServices;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilterEntryPoint;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.acegi.filter.OssAuthenticationProcessingFilter;

/**
 * <li>oss 登陸認證處理接口處理</li> </p> 主要作用是在發生認證異常時，取消登陸信息
 *
 * @author yangpeng 2008-10-14 下午03:53:10
 */
public class OssAuthenticationProcessingFilterEntryPoint extends AuthenticationProcessingFilterEntryPoint {
    protected static final Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    private RememberMeServices rememberMeServices;

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.ui.webapp.AuthenticationProcessingFilterEntryPoint#commence(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, org.acegisecurity.AuthenticationException)
     */
    @Override
    public void commence(ServletRequest request, ServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        log.error("auth error:", authException);
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            rememberMeServices.loginFail((HttpServletRequest) request, (HttpServletResponse) response);
        }
        super.commence(request, response, authException);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.acegisecurity.ui.webapp.AuthenticationProcessingFilterEntryPoint#determineUrlToUseForThisRequest(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.acegisecurity.AuthenticationException)
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) {
        String url = super.determineUrlToUseForThisRequest(request, response, exception);
        String source = request.getParameter(OssAuthenticationProcessingFilter.SOURCE_CODE);
        if (StringUtils.isNotEmpty(source)) {
            if (StringUtils.indexOf(url, '?') > -1) {
                url = url.concat("&").concat(OssAuthenticationProcessingFilter.SOURCE_CODE).concat("=").concat(source);
            }
            else {
                url = url.concat("?").concat(OssAuthenticationProcessingFilter.SOURCE_CODE).concat("=").concat(source);
            }
        }
        return url;
    }

    public RememberMeServices getRememberMeServices() {
        return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }
}
