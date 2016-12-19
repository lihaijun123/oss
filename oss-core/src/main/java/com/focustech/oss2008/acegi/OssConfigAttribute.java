package com.focustech.oss2008.acegi;

import java.util.Set;

import org.acegisecurity.ConfigAttribute;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-1 下午02:31:33
 */
public interface OssConfigAttribute extends ConfigAttribute {
    public static final int ATTRIBUTE_TYPE_ROLE = 1;
    public static final int ATTRIBUTE_TYPE_USER = 2;

    /**
     * 取配置信息的範圍控制權限
     *
     * @return
     */
    public String getScope();

    /**
     * 取配置信息的參數名稱
     */
    public Set<String> getParameterNames();

    /**
     * 是否是使用sql配置範圍權限
     */
    public boolean isSqlConfig();

    /**
     * 是否是使用java配置範圍權限
     */
    public boolean isJavaConfig();

    /**
     * 驗證此配置是否含義完全的權限
     */
    public boolean hasFullAuthory();

    /**
     * 取得配置信息的對應類型,可能此權限會賦予某個角色或某個用戶
     */
    public int getAttributeType();
}
