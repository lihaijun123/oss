package com.focustech.oss2008.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.model.OssAdminPortlet;

/**
 * <li>Portlet DAO</li>
 *
 * @author pangyihong
 */
@Repository
public interface OssAdminPortletDao<T> extends BaseHibernateDao<T> {

    /**
     * 根據用戶ID取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> selectPortletByUserId(String userId);

    /**
     * 根據用戶ID和Portlet類型取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param type Portlet類型
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> selectPortletByUserIdAndType(String userId, String type);

    /**
     * 取得用戶設置的列模式
     *
     * @param userId 用戶ID
     * @return 自定義的Portlet列表
     */
    public String selectPortletColumnMode(String userId);

    /**
     * 根據用戶ID和Portlet類型列表取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param types Portlet類型列表
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> selectPortletByUserIdAndTypes(String userId, String[] types);

    /**
     * 保存用戶自定義的Portlet列表，如果Portlet列表為空，則刪除該用戶原來所有指定類型Portlet
     *
     * @param userId 用戶ID
     * @param lstPortlet 用戶自定義的Portlet列表
     * @param portletType 指定要刪除的Portlet類型，如果為空，則刪除簡單配置的Portlet信息
     * @param portletType 指定要保存的列模式
     * @return 更新記錄數
     */
    public int[] savePortlets(String userId, List<OssAdminPortlet> lstPortlet, String portletType, String columnType);

    /**
     * 更新指定的Portlet列表的顯示順序
     *
     * @param lstPortlet 用戶自定義的Portlet列表
     * @param columnType 待更新的列模式,如果不更新列模式，該值為空
     */
    public int[] updatePortletsOrder(List<OssAdminPortlet> lstPortlet, String columnType);

    /**
     * 插入指定的Portlet列表
     *
     * @param lstPortlet 用戶自定義的Portlet列表
     * @param isDeleteAll 插入前是否刪除原有記錄
     */
    public int[] insertPortlets(List<OssAdminPortlet> lstPortlet, boolean isDeleteAll);
}
