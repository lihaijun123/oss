package com.focustech.oss2008.acegi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.context.HttpSessionContextIntegrationFilter;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.util.Assert;

/**
 * <li>oss session 上下文環境filter</li>
 * <p>
 * 首先判斷有沒有cookie,如果有,則按照正常的登陸流程登陸。如果沒有則需要重新登陸。
 *
 * @see org.acegisecurity.context.HttpSessionContextIntegrationFilter
 * @author yangpeng 2008-9-22 下午05:19:20
 */
public class OssHttpSessionContextIntegrationFilter extends HttpSessionContextIntegrationFilter {
    // private String cookieName;
    static final String FILTER_APPLIED = "__acegi_session_integration_filter_applied";

    /**
     * @throws ServletException
     */
    public OssHttpSessionContextIntegrationFilter() throws ServletException {
        super();
    }

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.context.HttpSessionContextIntegrationFilter#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // Assert.notNull(
        // cookieName,
        // "cookieName must be not null,and equals with RememberMeServices.cookieName if there be");
        super.afterPropertiesSet();
    }

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.context.HttpSessionContextIntegrationFilter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        Assert.isInstanceOf(HttpServletRequest.class, req, "ServletRequest must be an instance of HttpServletRequest");
        Assert.isInstanceOf(HttpServletResponse.class, res,
                "ServletResponse must be an instance of HttpServletResponse");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getAttribute(FILTER_APPLIED) != null) {
            // ensure that filter is only applied once per request
            chain.doFilter(request, response);
            return;
        }
        SecurityContext contextBeforeChainExecution = generateNewContext();// 強行設置使用新的用戶信息,不從session中取得
        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
        try {
            // This is the only place in this class where SecurityContextHolder.setContext() is called
            SecurityContextHolder.setContext(contextBeforeChainExecution);
            chain.doFilter(request, response);
        }
        finally {
            // Crucial removal of SecurityContextHolder contents - do this before anything else.
            SecurityContextHolder.clearContext();
            request.removeAttribute(FILTER_APPLIED);
            if (logger.isDebugEnabled()) {
                logger.debug("SecurityContextHolder now cleared, as request processing completed");
            }
        }
    }
    // /**
    // * 判斷當前用戶是否有cookie
    // */
    // public boolean hasCookie(HttpServletRequest request)
    // {
    // Cookie[] cookies= request.getCookies();
    // if ((cookies == null) || (cookies.length == 0))
    // {
    // return false;
    // }
    // for (int i= 0; i < cookies.length; i++)
    // {
    // if (cookieName.equals(cookies[i].getName()))
    // {
    // return true;
    // }
    // }
    // return false;
    // }
    // public String getCookieName()
    // {
    // return cookieName;
    // }
    // public void setCookieName(String cookieName)
    // {
    // this.cookieName= cookieName;
    // }
}
