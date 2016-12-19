package com.focustech.oss2008.acegi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.AcegiSecurityException;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.InsufficientAuthenticationException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.AccessDeniedHandler;
import org.acegisecurity.ui.AccessDeniedHandlerImpl;
import org.acegisecurity.ui.ExceptionTranslationFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.focustech.oss2008.Constants;

/**
 * <li>Acegi异常处理类</li>
 */
public class OssExceptionTranslationFilter extends ExceptionTranslationFilter {
    protected static final Log logger = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    public static final String ACEGI_SECURITY_ACCESS_DENIED_EXCEPTION_KEY = "ACEGI_SECURITY_403_EXCEPTION";
    private String errorPage;
    private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("HttpServletRequest required");
        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("HttpServletResponse required");
        }
        try {
            chain.doFilter(request, response);
            if (logger.isDebugEnabled()) {
                logger.debug("Chain processed normally");
            }
        }
        catch (AuthenticationException ex) {
            handleException(request, response, chain, ex);
        }
        catch (AccessDeniedException ex) {
            handleException(request, response, chain, ex);
        }
        catch (ServletException ex) {
            if (ex.getRootCause() instanceof AuthenticationException
                    || ex.getRootCause() instanceof AccessDeniedException) {
                handleException(request, response, chain, (AcegiSecurityException) ex.getRootCause());
            }
            else {
                ossHandleException(request, response, chain, ex);
            }
        }
        catch (IOException ex) {
            ossHandleException(request, response, chain, ex);
        }
        catch (RuntimeException ex) {
            ossHandleException(request, response, chain, ex);
        }
    }

    private void handleException(ServletRequest request, ServletResponse response, FilterChain chain,
            AcegiSecurityException exception) throws IOException, ServletException {
        if (exception instanceof AuthenticationException) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authentication exception occurred; redirecting to authentication entry point", exception);
            }
            sendStartAuthentication(request, response, chain, (AuthenticationException) exception);
        }
        else if (exception instanceof AccessDeniedException) {
            if (getAuthenticationTrustResolver().isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Access is denied (user is anonymous); redirecting to authentication entry point",
                            exception);
                }
                sendStartAuthentication(request, response, chain, new InsufficientAuthenticationException(
                        "Full authentication is required to access this resource"));
            }
            else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Access is denied (user is not anonymous); delegating to AccessDeniedHandler",
                            exception);
                }
                accessDeniedHandler.handle(request, response, (AccessDeniedException) exception);
            }
        }
    }

    private void ossHandleException(ServletRequest request, ServletResponse response, FilterChain chain,
            Exception exception) throws IOException, ServletException {
        logger.error(exception);
        if (errorPage != null) {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	if(null == auth){
        		//seesion可能过期，用户需要重新登录
        		errorPage = "login_error.do";
        	}
            // Put exception into request scope (perhaps of use to a view)
            ((HttpServletRequest) request).setAttribute(ACEGI_SECURITY_ACCESS_DENIED_EXCEPTION_KEY, exception);
            // Perform RequestDispatcher "forward"
            RequestDispatcher rd = request.getRequestDispatcher(errorPage);
            rd.forward(request, response);
        }
        else if (!response.isCommitted()) {
            // Send 403 (we do this after response has been written)
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
        }
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    public AccessDeniedHandler getAccessDeniedHandler() {
        return accessDeniedHandler;
    }

    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        Assert.notNull(accessDeniedHandler, "AccessDeniedHandler required");
        this.accessDeniedHandler = accessDeniedHandler;
    }
}
