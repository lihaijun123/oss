package com.focustech.oss2008.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.UserOAInfoDao;
import com.focustech.oss2008.model.OssAdminUserOainfo;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.UserOAInfoService;

/**
 * 用戶信息與OA信息同步管理UserOAInfoServiceImpl.java
 *
 * @author jibin
 */
@Service
public class UserOAInfoServiceImpl extends AbstractServiceSupport implements UserOAInfoService {
    @Autowired
    private UserOAInfoDao<OssAdminUserOainfo> userOAInfoDao;

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserOAInfoService#getUser(java.lang.String)
     */
    public OssAdminUserOainfo getUser(String userId) {
        return userOAInfoDao.select(userId);

    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserOAInfoService#saveOAInfo(com.focustech.oss2008.model.OssAdminUserOainfo)
     */
    public void saveOAInfo(OssAdminUserOainfo info) {
        userOAInfoDao.insertOrUpdate(info);

    }

}
