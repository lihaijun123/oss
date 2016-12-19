package com.focustech.oss2008.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.focustech.common.utils.DateUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.Constants;


/**
 * <li>OpenSessionInViewFilter</li>
 * <p>
 * 增加了操作時間日志
 *
 * @author yangpeng 2008-11-2 下午12:33:38
 */
public class OssOpenSessionInViewFilter extends OpenSessionInViewFilter {
    protected Log logger = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    private boolean enablePerformanceLog = false;
    private boolean enableParameterLog = false;
    private long valve;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Date startDate = new Date();
        if (enablePerformanceLog) {
            try {
                StringBuffer message = new StringBuffer();
                StringBuffer params = null;
                if (enableParameterLog) {
                    params = new StringBuffer();
                    params.append("parameters:");
                    for (Enumeration names = request.getParameterNames(); names.hasMoreElements();) {
                        Object name = names.nextElement().toString();
                        params.append(name).append(":").append(request.getParameter(name.toString())).append(";");
                    }
                }
                message.append("rquest :").append(request.getRequestURI());
                if (enableParameterLog) {
                    message.append(". ").append(params);
                }
                message.append(". Enter.");
                logger.info(message.toString());
            }
            catch (Exception e) {
                logger.error("write log error:", e);
            }
        }
        SessionFactory sessionFactory = lookupSessionFactory(request);
        boolean participate = false;
        if (isSingleSession()) {
            // single session mode
            if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
                // Do not modify the Session: just set the participate flag.
                participate = true;
            }
            else {
                logger.debug("Opening single Hibernate Session in OpenSessionInViewFilter");
                Session session = getSession(sessionFactory);
                TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
            }
        }
        else {
            // deferred close mode
            if (SessionFactoryUtils.isDeferredCloseActive(sessionFactory)) {
                // Do not modify deferred close: just set the participate flag.
                participate = true;
            }
            else {
                SessionFactoryUtils.initDeferredClose(sessionFactory);
            }
        }
        try {
        	HttpSession session = request.getSession();
			Enumeration attributeNames = session.getAttributeNames();
			List<String> redirectAttributeNames = new ArrayList<String>();
        	while(attributeNames.hasMoreElements()){
        		String name = TCUtil.sv(attributeNames.nextElement());
        		if(name.contains("message") || name.contains("msg")){
        			Object value = session.getAttribute(name);
        			request.setAttribute(name, value);
        			redirectAttributeNames.add(name);
        		}
        	}
        	if(!redirectAttributeNames.isEmpty()){
        		for(String name : redirectAttributeNames){
        			session.removeAttribute(name);
        		}
        		redirectAttributeNames.clear();
        	}
            filterChain.doFilter(request, response);
        }
        finally {
            if (!participate) {
                if (isSingleSession()) {
                    // single session mode
                    SessionHolder sessionHolder =
                            (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
                    logger.debug("Closing single Hibernate Session in OpenSessionInViewFilter");
                    closeSession(sessionHolder.getSession(), sessionFactory);
                }
                else {
                    // deferred close mode
                    SessionFactoryUtils.processDeferredClose(sessionFactory);
                }
            }
            if (enablePerformanceLog) {
                Date endDate = new Date();
                StringBuffer message = new StringBuffer();
                long time = DateUtils.getInterval(startDate, endDate);
                if (time > valve) {
                    message.append("long time process rquest :").append(request.getRequestURI()).append(
                            ". process time is :").append(time).append(" ms.Exit.");
                    logger.error(message.toString());
                }
                else {
                    message.append("rquest :").append(request.getRequestURI()).append(". process time is :").append(
                            time).append(" ms.Exit.");
                    logger.info(message.toString());
                }
            }
        }
    }

    public boolean isEnablePerformanceLog() {
        return enablePerformanceLog;
    }

    public void setEnablePerformanceLog(boolean enablePerformanceLog) {
        this.enablePerformanceLog = enablePerformanceLog;
    }

    public long getValve() {
        return valve;
    }

    public void setValve(long valve) {
        this.valve = valve;
    }

    public boolean isEnableParameterLog() {
        return enableParameterLog;
    }

    public void setEnableParameterLog(boolean enableParameterLog) {
        this.enableParameterLog = enableParameterLog;
    }
}
