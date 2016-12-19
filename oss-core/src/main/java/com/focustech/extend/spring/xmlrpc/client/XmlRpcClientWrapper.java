package com.focustech.extend.spring.xmlrpc.client;

/**
 * <li>xml rpc client d的包裝類</li>
 *
 * @author yangpeng 2008-8-11 下午04:27:35
 */
public interface XmlRpcClientWrapper {
    String ROLE = XmlRpcClientWrapper.class.getName();

    /**
     * 執行服務
     *
     * @param serviceName 服務名稱，例如︰"Calculator.add"
     * @param parameters 參數
     * @return
     */
    public Object execute(String serviceName, Object[] parameters);
}
