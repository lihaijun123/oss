package com.focustech.oss2008.dao.impl;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.UserOAInfoDao;
import com.focustech.oss2008.model.OssAdminUserOainfo;

/**
 *
 * @author jibin
 */
@Repository
public class UserOAInfoDaoImpl extends OssHibernateDaoSupport<OssAdminUserOainfo> implements
        UserOAInfoDao<OssAdminUserOainfo> {
}
