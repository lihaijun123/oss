package com.focustech.oss2008.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.ProxyUserDao;
import com.focustech.oss2008.model.OssAdminProxyUser;

@Repository
public class ProxyUserDaoImpl extends OssHibernateDaoSupport<OssAdminProxyUser> implements
        ProxyUserDao<OssAdminProxyUser> {
    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.ProxyusersDao#selectAllProxyuser()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminProxyUser> selectAllProxyuser() {
        return getCurrentSession().createQuery("from OssAdminProxyUser").list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.ProxyusersDao#selectUsefulProxyuser()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminProxyUser> selectUsefulProxyuser() {
        return getCurrentSession().createQuery("from OssAdminProxyUser where endTime>=sysdate() and active=?").setString(
                0, Constants.DEPARTMENT_ACTIVE).list();
    }

    @SuppressWarnings("unchecked")
    public List<OssAdminProxyUser> selectDisabledProxyuser() {
        return getCurrentSession().createQuery("from OssAdminProxyUser where endTime<sysdate() or active=?").setString(0,
                Constants.DEPARTMENT_FORBIDDEN).list();
    }
}
