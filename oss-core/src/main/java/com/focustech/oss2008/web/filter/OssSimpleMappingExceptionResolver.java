package com.focustech.oss2008.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * <li>異常處理器</li>
 * <p>
 * 將記錄日志的級別提高為error
 *
 * @author yangpeng 2009-1-7 上午11:45:31
 */
public class OssSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private Log warnLogger;

    @Override
    protected void logException(Exception ex, HttpServletRequest request) {
        if (this.warnLogger != null && this.warnLogger.isErrorEnabled()) {
            this.warnLogger.error(buildLogMessage(ex, request), ex);
        }
    }

    /**
     * Set the log category for warn logging. The name will be passed to the underlying logger implementation through
     * Commons Logging, getting interpreted as log category according to the logger's configuration.
     * <p>
     * Default is no warn logging. Specify this setting to activate warn logging into a specific category.
     * Alternatively, override the {@link #logException} method for custom logging.
     *
     * @see org.apache.commons.logging.LogFactory#getLog(String)
     * @see org.apache.log4j.Logger#getLogger(String)
     * @see java.util.logging.Logger#getLogger(String)
     */
    public void setWarnLogCategory(String loggerName) {
        this.warnLogger = LogFactory.getLog(loggerName);
    }
}
