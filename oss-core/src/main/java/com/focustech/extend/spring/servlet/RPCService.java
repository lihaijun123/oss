/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.extend.spring.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.common.XmlRpcHttpRequestConfig;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

/**
 *  asdf.java
 *
 * @author sunjingyu
 */
public class RPCService extends XmlRpcServlet {
	 /**
	 *
	 */
	private static final long serialVersionUID = -3136728327459664330L;
	private static final Logger log = Logger.getLogger("rpc_service");
	    private String userName;
	    private String password;
	    private String serviceName;
	    private String className;

	    private boolean isAuthenticated(String pUserName, String pPassword) {
	        boolean flag = userName.equals(pUserName) && password.equals(pPassword);
	        if (flag) {
	            log.info("userName and password is right");
	        }
	        else {
	            log.info("userName and password is wrong.");
	        }
	        return flag;
	    }

	    /*
	     * (non-Javadoc)
	     * @see org.apache.xmlrpc.webserver.XmlRpcServlet#newXmlRpcHandlerMapping()
	     */

	    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
	        // TODO Auto-generated method stub
	        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
	        //
	        AbstractReflectiveHandlerMapping.AuthenticationHandler handler = new AuthenticationHandler() {

	            @Override
	            public boolean isAuthorized(XmlRpcRequest pRequest) throws XmlRpcException {
	                // TODO Auto-generated method stub
	                log.info("start to authorize");
	                XmlRpcHttpRequestConfig config = (XmlRpcHttpRequestConfig) pRequest.getConfig();
	                return isAuthenticated(config.getBasicUserName(), config.getBasicPassword());
	            }
	        };
	        mapping.setAuthenticationHandler(handler);
	        if (getRequestProcessorFactoryFactory() != null) {
	            mapping.setRequestProcessorFactoryFactory(getRequestProcessorFactoryFactory());
	        }
	        if (getTypeConverterFactory() != null) {
	            mapping.setTypeConverterFactory(getTypeConverterFactory());
	        }
	        else {
	            mapping.setTypeConverterFactory(getXmlRpcServletServer().getTypeConverterFactory());
	        }
	        mapping.setVoidMethodEnabled(getXmlRpcServletServer().getConfig().isEnabledForExtensions());
	        Map<String, String> pMap = new HashMap<String, String>();
	        pMap.put(serviceName, className);
	        log.info("serviceName=" + serviceName + ", claslsName=" + className);
	        mapping.load(Thread.currentThread().getContextClassLoader(), pMap);
	        log.info("load success.");
	        return mapping;
	    }

	    /*
	     * (non-Javadoc)
	     * @see org.apache.xmlrpc.webserver.XmlRpcServlet#newXmlRpcServer(javax.servlet.ServletConfig)
	     */

	    protected XmlRpcServletServer newXmlRpcServer(ServletConfig pConfig) throws XmlRpcException {
	        // TODO Auto-generated method stub
	        return super.newXmlRpcServer(pConfig);
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getServiceName() {
	        return serviceName;
	    }

	    public void setServiceName(String serviceName) {
	        this.serviceName = serviceName;
	    }

	    public String getClassName() {
	        return className;
	    }

	    public void setClassName(String className) {
	        this.className = className;
	    }
}
