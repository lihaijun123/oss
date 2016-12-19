package com.focustech.oss2008.service;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * <pre>
 * xmlrpc服務請求組件接口
 * </pre>
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-30 上午09:41:46
 */
public interface OssXmlRpcService {
    Object m_lock = new Object();

    void init();

    /**
     * 調用xmlrpc服務器對應服務方法
     *
     * @param method 服務器應用對應方法
     * @param params 方法參數
     * @return 返回服務器方法返回信息
     */
    Object call(String pMethod, Object[] pParams) throws Exception;
}
