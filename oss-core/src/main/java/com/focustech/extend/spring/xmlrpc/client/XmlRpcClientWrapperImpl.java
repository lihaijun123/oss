package com.focustech.extend.spring.xmlrpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcLiteHttpTransportFactory;

import com.focustech.common.utils.StringUtils;

/**
 * <li>xml rpc客戶端</li>
 *
 * @author yangpeng 2008-8-11 下午04:27:54
 */
public class XmlRpcClientWrapperImpl implements XmlRpcClientWrapper {
    protected Log log = LogFactory.getLog(XmlRpcClientWrapperImpl.class);
    /** 遠程服務的URL地址，默認http://localhost/xmlrpc */
    private String url = StringUtils.addHttpUrlPrefix("localhost/xmlrpc");
    /** 最多的連接嘗試次數，默認3次 */
    private int maxTry = 3;
    /** 服務連接超時的時長（單位︰毫秒），默認30000ms */
    private int connectionTimeout = 30000;
    private XmlRpcClient client;
    /** 是否支持擴展，默認︰true */
    private boolean enabledForExtensions = true;
    /** 默認︰false */
    private boolean enabledForExceptions = false;
    /** 默認︰false */
    private boolean contentLengthOptional = false;
    /** 字符編碼，默認︰GBK */
    private String basicEncoding = "GBK";
    /** 連接密碼 */
    private String basicPassword;
    /** 連接密碼用戶名 */
    private String basicUserName;

    @PostConstruct
    public void init() {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL(url));
            config.setEnabledForExtensions(enabledForExtensions);
            config.setEnabledForExceptions(enabledForExceptions);
            config.setConnectionTimeout(connectionTimeout);
            config.setContentLengthOptional(contentLengthOptional);
            config.setBasicEncoding(basicEncoding);
            config.setBasicPassword(basicPassword);
            config.setBasicUserName(basicUserName);
            client = new XmlRpcClient();
            client.setConfig(config);
            client.setTransportFactory(new XmlRpcLiteHttpTransportFactory(client));
        }
        catch (MalformedURLException e) {
            log.error(e);
            throw new IllegalArgumentException("the url:" + url + " could not determine the server!");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.extend.spring.xmlrpc.client.XmlRpcClientWrapper#execute(java.lang.String, java.lang.Object[])
     */
    public Object execute(String serviceName, Object[] parameters) {
        Object mOutput = null;
        try {
            boolean run = false;
            Throwable runE = null;
            for (int i = 0; i < maxTry; i++) {
                try {
                    mOutput = client.execute(serviceName, parameters);
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
            String param = "";
            for (int i = 0; null != parameters && i < parameters.length; i++) {
                param += parameters[i].toString();
            }
            log.error("service:" + serviceName + " parameters:" + param + " error!", e);
            throw new IllegalArgumentException("service:" + serviceName + " error!");
        }
        return mOutput;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnabledForExtensions() {
        return enabledForExtensions;
    }

    public void setEnabledForExtensions(boolean enabledForExtensions) {
        this.enabledForExtensions = enabledForExtensions;
    }

    public boolean isEnabledForExceptions() {
        return enabledForExceptions;
    }

    public void setEnabledForExceptions(boolean enabledForExceptions) {
        this.enabledForExceptions = enabledForExceptions;
    }

    public int getMaxTry() {
        return maxTry;
    }

    public void setMaxTry(int maxTry) {
        this.maxTry = maxTry;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public boolean isContentLengthOptional() {
        return contentLengthOptional;
    }

    public void setContentLengthOptional(boolean contentLengthOptional) {
        this.contentLengthOptional = contentLengthOptional;
    }

    public String getBasicEncoding() {
        return basicEncoding;
    }

    public void setBasicEncoding(String basicEncoding) {
        this.basicEncoding = basicEncoding;
    }

    public String getBasicPassword() {
        return basicPassword;
    }

    public void setBasicPassword(String basicPassword) {
        this.basicPassword = basicPassword;
    }

    public String getBasicUserName() {
        return basicUserName;
    }

    public void setBasicUserName(String basicUserName) {
        this.basicUserName = basicUserName;
    }
}
