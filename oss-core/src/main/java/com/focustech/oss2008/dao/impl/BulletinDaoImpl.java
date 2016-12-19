package com.focustech.oss2008.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.BulletinDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.OssAdminBulletin;

@Repository
public class BulletinDaoImpl extends OssHibernateDaoSupport<OssAdminBulletin> implements BulletinDao<OssAdminBulletin> {
    @SuppressWarnings("unchecked")
    public List<OssAdminBulletin> selectAllBulletins(String model, int maxResult) {
        String hql = "from OssAdminBulletin where bulletinModel in (?,?) and active = ? order by modifiedTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setString(0, model);
        query.setString(1, Constants.SYSTEM_MODEL_ALL);
        query.setString(2, Constants.ACTIVE_TYPE_ALLOW);
        setTop(query, maxResult);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<OssAdminBulletin> selectAllBulletins(int maxResult) {
        String hql = "from OssAdminBulletin where active = ? order by modifiedTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setString(0, Constants.ACTIVE_TYPE_ALLOW);
        setTop(query, maxResult);
        return query.list();
    }

    public boolean hasNewActiveBulletin(String model) {
        String hql =
                "from OssAdminBulletin where bulletinModel in (?,?) and active = ? and modifiedTime>to_char(sysdate(),'yyyy-MM-dd') order by modifiedTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setString(0, model);
        query.setString(1, Constants.SYSTEM_MODEL_ALL);
        query.setString(2, Constants.ACTIVE_TYPE_ALLOW);
        return query.list().size() > 0;
    }

    /**
     *
     * @param query
     * @param maxResult
     */
    private void setTop(Query query, int maxResult) {
        if (maxResult > 0) {
            query.setFirstResult(0);
            query.setMaxResults(maxResult);
        }
    }
}
