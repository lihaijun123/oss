/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.oss2008.rpc;

import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.springframework.util.Assert;
/**
 * CommonsXmlRpcClient.java
 *
 * @author xiayuqi
 */
public class CommonsXmlRpcClient {
    protected static final Log log = LogFactory.getLog(CommonsXmlRpcClient.class);
    private String serverURL;
    private String userName;
    private String password;
    private Integer timeout;
    private String encoding;
    private Integer maxTry;
    private XmlRpcClient xmlRpcClient;
    private Boolean enabledForExtensions;
    private Boolean contentLengthOptional;
    private final int DEFAULT_TIMEOUT = 30000;
    private final boolean DEFAULT_ENABLEDFOREXTENSIONS = true;
    private final boolean DEFAULT_CONTENTLENGTHOPTIONAL = false;
    private final String DEFAULT_ENCODING = "UTF-8";
    private final int DEFAULT_MAX_TRY = 1;

    //@PostConstruct
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(serverURL, "serverURL must be given!");
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(serverURL));
        log.debug("serverURL:" + serverURL);
        config.setConnectionTimeout(timeout != null ? timeout : DEFAULT_TIMEOUT);
        config.setEnabledForExtensions(enabledForExtensions != null ? enabledForExtensions
                : DEFAULT_ENABLEDFOREXTENSIONS);
        config.setContentLengthOptional(contentLengthOptional != null ? contentLengthOptional
                : DEFAULT_CONTENTLENGTHOPTIONAL);
        config.setBasicEncoding(StringUtils.defaultIfEmpty(encoding, DEFAULT_ENCODING));
        if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)) {
            config.setBasicUserName(userName);
            config.setBasicPassword(password);
            log.debug("userName: " + userName);
            log.debug("password: " + password);
        }
        xmlRpcClient = new XmlRpcClient();
        xmlRpcClient.setConfig(config);
        xmlRpcClient.setTransportFactory(new XmlRpcCommonsTransportFactory(xmlRpcClient));
        if ((maxTry == null) || (maxTry <= 0)) {
            maxTry = DEFAULT_MAX_TRY;
        }
    }

    public Boolean getContentLengthOptional() {
        return contentLengthOptional;
    }

    public Boolean getEnabledForExtensions() {
        return enabledForExtensions;
    }

    public String getEncoding() {
        return encoding;
    }

    public Integer getMaxTry() {
        return maxTry;
    }

    public String getPassword() {
        return password;
    }

    public String getServerURL() {
        return serverURL;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public String getUserName() {
        return userName;
    }

    public void setContentLengthOptional(Boolean contentLengthOptional) {
        this.contentLengthOptional = contentLengthOptional;
    }

    public void setEnabledForExtensions(Boolean enabledForExtensions) {
        this.enabledForExtensions = enabledForExtensions;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setMaxTry(Integer maxTry) {
        this.maxTry = maxTry;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    protected Object execute(String methodName, Object[] params) {
        if (xmlRpcClient == null) {
            throw new IllegalStateException("xmlRpcClient can not be null");
        }
        Object result = null;
        try {
            for (int i = 0; i < maxTry; i++) {
                try {
                    Object mOutput = xmlRpcClient.execute(methodName, params);
                    result = mOutput;
                    break;
                }
                catch (Throwable e) {
                    e.printStackTrace();
                }
                Thread.sleep(1000);
            }
        }
        catch (Exception e) {
            log.error("execute method " + methodName + "error ", e);
        }
        return result;
    }
}
