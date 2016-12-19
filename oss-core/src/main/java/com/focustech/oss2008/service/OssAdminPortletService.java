package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.model.OssAdminPortlet;

/**
 * <li>用戶自定義Portlet服務</li>
 *
 * @author pangyihong 2010-7-20 上午10:32:00
 */
public interface OssAdminPortletService {

    /**
     * 保存用戶自定義Portlet
     *
     * @param userId 登錄者ID
     * @param lstPortlet 待保存的Portlet列表
     * @param portletType 指定要刪除的Portlet類型，如果為空，則刪除簡單配置的Portlet信息
     * @param columnType 待更新的列模式,如果不更新列模式，該值為空
     */
    public void saveUserPortlet(String userId, List<OssAdminPortlet> lstPortlet, String portletType, String columnType);

    /**
     * 更新用戶自定義Portlet的顯示順序
     *
     * @param lstPortlet 待更新的Portlet列表
     * @param columnType 待更新的列模式,如果不更新列模式，該值為空
     */
    public void updateUserPortletOrder(List<OssAdminPortlet> lstPortlet, String columnType);

    /**
     * 新增用戶自定義Portlet
     *
     * @param lstPortlet
     * @param isDeleteAll 新增前是否刪除原有記錄
     */
    public void addUserPortlets(List<OssAdminPortlet> lstPortlet, boolean isDeleteAll);

    /**
     * 取得用戶自定義Portlet
     *
     * @param userId
     * @return
     */
    public List<OssAdminPortlet> getPortletsByUserId(String userId);

    /**
     * 根據Portlet ID取得用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public OssAdminPortlet getPortletByPortletId(String portletId);

    /**
     * 取得用戶設置的列模式
     *
     * @param userId 用戶ID
     * @return 列模式
     */
    public String getPortletColumnMode(String userId);

    /**
     * 新增用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public void insertPortlet(OssAdminPortlet portlet);

    /**
     * 更新用戶自定義Portlet
     *
     * @param portletId
     */
    public void updatePortlet(OssAdminPortlet portlet);

    /**
     * 刪除用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public void deletePortlet(String portletId);

    /**
     * 根據用戶ID和Portlet類型取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param type Portlet類型
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> getPortletsByUserIdAndType(String userId, String type);

    /**
     * 根據用戶ID和Portlet類型列表取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param types Portlet類型列表
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> getPortletsByUserIdAndTypes(String userId, String[] types);
}
