package com.focustech.oss2008.service.impl;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.common.utils.DateUtils;
import com.focustech.oss2008.Constants;


/**
 * <li></li>
 *
 * @author yangpeng 2008-7-23 上午11:24:06
 */
public class LogInterceptor implements MethodInterceptor {
    private long valve;
    private boolean enterLogEnabled = false;
    private boolean exitLogEnabled = false;
    private Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);

    /*
     * (non-Javadoc)
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (log.isDebugEnabled()) {
            StringBuffer message = new StringBuffer();
            message.append(invocation.getThis().getClass().getName()).append(".").append(
                    invocation.getMethod().getName());
            Date start = new Date();
            if (enterLogEnabled && log.isDebugEnabled()) {
                StringBuffer enterMessage = new StringBuffer();
                log.debug(enterMessage.append(message).append(" Enter."));
            }
            Object result = invocation.proceed();
            if (enterLogEnabled && log.isDebugEnabled()) {
                StringBuffer exitMessage = new StringBuffer();
                log.debug(exitMessage.append(message).append(" Exit."));
            }
            Date end = new Date();
            long time = DateUtils.getInterval(start, end);
            message.append(" proceed time:").append(time).append("ms");
            log.debug(message.toString());
            if (time > valve) {
                StringBuffer error = new StringBuffer();
                error.append("long proceed time!").append(message);
                log.error(error.toString());
            }
            return result;
        }
        else {
            return invocation.proceed();
        }
    }

    public long getValve() {
        return valve;
    }

    public void setValve(long valve) {
        this.valve = valve;
    }

    public boolean isEnterLogEnabled() {
        return enterLogEnabled;
    }

    public void setEnterLogEnabled(boolean enterLogEnabled) {
        this.enterLogEnabled = enterLogEnabled;
    }

    public boolean isExitLogEnabled() {
        return exitLogEnabled;
    }

    public void setExitLogEnabled(boolean exitLogEnabled) {
        this.exitLogEnabled = exitLogEnabled;
    }
}
