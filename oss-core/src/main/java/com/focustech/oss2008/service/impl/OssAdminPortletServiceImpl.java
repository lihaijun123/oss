package com.focustech.oss2008.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.OssAdminPortletDao;
import com.focustech.oss2008.model.OssAdminPortlet;
import com.focustech.oss2008.service.OssAdminPortletService;

/**
 * <li>用戶自定義Portlet服務實現</li>
 *
 * @author pangyihong 2010-7-20 上午10:40:00
 */
@Service
public class OssAdminPortletServiceImpl implements OssAdminPortletService {
    @Autowired
    OssAdminPortletDao<OssAdminPortlet> portletDao;

    /**
     * 保存用戶自定義Portlet
     *
     * @param userId
     * @param lstPortlet
     * @param portletType
     */
    public void saveUserPortlet(String userId, List<OssAdminPortlet> lstPortlet, String portletType, String columnType) {
        portletDao.savePortlets(userId, lstPortlet, portletType, columnType);
    }

    /**
     * 更新用戶自定義Portlet的顯示順序
     *
     * @param lstPortlet
     * @param columnType
     */
    public void updateUserPortletOrder(List<OssAdminPortlet> lstPortlet, String columnType) {
        portletDao.updatePortletsOrder(lstPortlet, columnType);
    }

    /**
     * 新增用戶自定義Portlet
     *
     * @param lstPortlet
     * @param isDeleteAll
     */
    public void addUserPortlets(List<OssAdminPortlet> lstPortlet, boolean isDeleteAll) {
        portletDao.insertPortlets(lstPortlet, isDeleteAll);
    }

    /**
     * 取得用戶自定義Portlet
     *
     * @param userId
     * @return
     */
    public List<OssAdminPortlet> getPortletsByUserId(String userId) {
        return portletDao.selectPortletByUserId(userId);
    }

    /**
     * 根據Portlet ID取得用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public OssAdminPortlet getPortletByPortletId(String portletId) {
        return portletDao.select(Long.valueOf(portletId));
    }

    /**
     * 取得用戶設置的列模式
     *
     * @param userId 用戶ID
     * @return 自定義的Portlet列表
     */
    public String getPortletColumnMode(String userId) {
        return portletDao.selectPortletColumnMode(userId);
    }

    /**
     * 新增用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public void insertPortlet(OssAdminPortlet portlet) {
        portletDao.insert(portlet);
    }

    /**
     * 更新用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public void updatePortlet(OssAdminPortlet portlet) {
        portletDao.update(portlet);
    }

    /**
     * 刪除用戶自定義Portlet
     *
     * @param portletId
     * @return
     */
    public void deletePortlet(String portletId) {
        portletDao.delete(Long.valueOf(portletId));
    }

    /**
     * 根據用戶ID和Portlet類型取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param type Portlet類型
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> getPortletsByUserIdAndType(String userId, String type) {
        return portletDao.selectPortletByUserIdAndType(userId, type);
    }

    /**
     * 根據用戶ID和Portlet類型列表取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param types Portlet類型列表
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> getPortletsByUserIdAndTypes(String userId, String[] types) {
        return portletDao.selectPortletByUserIdAndTypes(userId, types);
    }
}
