package com.focustech.oss2008.service.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.service.OssXmlRpcService;

/**
 * Copyright (c) 2008, focustech All rights reserved 通過xmlrpc調用服務器方法類，此類線程不安全，在多線程情況下請不要使用singleton實例
 *
 * @author tc-hexuey
 * @version 1.0 2008-7-25 上午10:05:56
 */
public class OssXmlRpcServiceImpl implements OssXmlRpcService {
    private XmlRpcClient rpclient;
    private int maxTry = 3;
    private int timeout = 30000;
    private String url = StringUtils.addHttpUrlPrefix("192.168.1.214/xmlrpc");
    private String encoding = "BIG5";
    private Map<String, String> cfgProps = new HashMap<String, String>();

    /** 初始化xmlrpc對象 */
    public void init() {
        if (null == rpclient) {
            synchronized (m_lock) {
                if (null == rpclient) {
                    try {
                        XmlRpcClientConfigImpl cfg = new XmlRpcClientConfigImpl();
                        cfg.setConnectionTimeout(getTimeout());// 30 sec
                        cfg.setEnabledForExtensions(true);// 允許使用XML-RPC擴展APACHE協議
                        cfg.setContentLengthOptional(false);// 可以不傳送ContentLength
                        cfg.setEncoding(getEncoding());
                        cfg.setServerURL(new URL(getUrl()));
                        if (cfgProps != null && cfgProps.isEmpty() == false) {
                            for (String key : cfgProps.keySet()) {
                                try {
                                    PropertyUtils.setProperty(cfg, key, cfgProps.get(key));
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        rpclient = new XmlRpcClient();
                        rpclient.setConfig(cfg);
                        rpclient.setTransportFactory(new XmlRpcCommonsTransportFactory(rpclient));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 調用xmlrpc服務器對應服務方法
     *
     * @param method 服務器應用對應方法
     * @param params 方法參數
     * @return 返回服務器方法返回信息
     */
    public Object call(String method, Object[] params) throws Exception {
        Object mOutput = null;
        try {
            boolean run = false;
            Throwable runE = null;
            for (int i = 0; i < maxTry; i++) {
                try {
                    mOutput = rpclient.execute(method, params);
                    run = true;
                    break;
                }
                catch (Throwable e) {
                    e.printStackTrace();
                    runE = e;
                }
                Thread.sleep(1000);
            }
            if (run == false) {
                throw new Exception("重復請求“" + maxTry + "”次後沒有成功。", runE);
            }
        }
        catch (Exception e) {
            throw e;
        }
        return mOutput;
    }

    // --------------------------------------------------------------------------------------------------------------
    public int getMaxTry() {
        return maxTry;
    }

    public void setMaxTry(int maxTry) {
        this.maxTry = maxTry;
    }

    public XmlRpcClient getRpclient() {
        return rpclient;
    }

    public void setRpclient(XmlRpcClient rpclient) {
        this.rpclient = rpclient;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Map<String, String> getCfgProps() {
        return cfgProps;
    }

    public void setCfgProps(Map<String, String> cfgProps) {
        this.cfgProps = cfgProps;
    }
}
