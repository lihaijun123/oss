package com.focustech.oss2008.acegi.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.ui.AccessDeniedHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.oss2008.Constants;

/**
 * <li></li>
 *
 */
public class OssAccessDeniedHandlerImpl implements AccessDeniedHandler {
    // ~ Static fields/initializers
    // =====================================================================================
    public static final String ACEGI_SECURITY_ACCESS_DENIED_EXCEPTION_KEY = "ACEGI_SECURITY_403_EXCEPTION";
    protected static final Log logger = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    // ~ Instance fields
    // ================================================================================================
    private String errorPage;

    // ~ Methods
    // ========================================================================================================
    public void handle(ServletRequest request, ServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        logger.error(accessDeniedException);
        if (errorPage != null) {
            // Put exception into request scope (perhaps of use to a view)
            ((HttpServletRequest) request).setAttribute(ACEGI_SECURITY_ACCESS_DENIED_EXCEPTION_KEY,
                    accessDeniedException);
            // Perform RequestDispatcher "forward"
            RequestDispatcher rd = request.getRequestDispatcher(errorPage);
            rd.forward(request, response);
        }
        else if (!response.isCommitted()) {
            // Send 403 (we do this after response has been written)
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException
                    .getMessage());
        }
    }

    /**
     * The error page to use. Must begin with a "/" and is interpreted relative to the current context root.
     *
     * @param errorPage the dispatcher path to display
     * @throws IllegalArgumentException if the argument doesn't comply with the above limitations
     */
    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("ErrorPage must begin with '/'");
        }
        this.errorPage = errorPage;
    }
}
