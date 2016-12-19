package com.focustech.oss2008.dao.impl;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.UserLoginLogDao;
import com.focustech.oss2008.model.OssAdminLoginLog;

/**
 * <li></li>
 *
 */
@Repository
public class UserLoginLogDaoImpl extends OssHibernateDaoSupport<OssAdminLoginLog> implements
        UserLoginLogDao<OssAdminLoginLog> {
}
