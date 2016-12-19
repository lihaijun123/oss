package com.focustech.oss2008.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.focustech.common.constant.OssAdminParConst;
import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.dao.OssAdminParameterDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.service.OssAdminParameterService;

/**
 * <li>参数dao实现{</li>
 *
 * @author xufei 2008-4-17
 */
@SuppressWarnings("unchecked")
@Repository
public class OssAdminParameterDaoImpl extends OssHibernateDaoSupport<OssAdminParameter> implements
        OssAdminParameterDao<OssAdminParameter> {
    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.OssAdminParameterDao#selectAllBaseParameters()
     */
    @Override
    public List<OssAdminParameter> selectAllBaseParameters() {
        return getCurrentSession().createQuery("from OssAdminParameter order by parameterType,webSite,parameterOrder")
                .list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.OssAdminParameterDao#selectParameter(java.lang .String, java.lang.String)
     */
    @Override
    public List<OssAdminParameter> selectParameters(String parameterType, String siteType) {
        Criteria crit = getCurrentSession().createCriteria(OssAdminParameter.class);
        crit.add(Restrictions.eq("parameterType", parameterType));
        crit.add(Restrictions.eq("webSite", siteType));
        crit.addOrder(Order.asc("parameterOrder"));
        return crit.list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.OssAdminParameterDao#selectParameters(java. lang.String)
     */
    @Override
    public List<OssAdminParameter> selectParameters(String parameterType) {
        Criteria crit = getCurrentSession().createCriteria(OssAdminParameter.class);
        crit.add(Restrictions.eq("parameterType", parameterType));
        crit.addOrder(Order.asc("webSite"));
        crit.addOrder(Order.asc("parameterOrder"));
        return crit.list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.OssAdminParameterDao# selectParameterByTypeKeyAndSite(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public OssAdminParameter selectParameterByTypeKeyAndSite(String parameterType, String parameterKey, String website) {
        Query query =
                getCurrentSession().createQuery(
                        "from OssAdminParameter where parameterType=? and parameterKey=? and webSite=?");
        query.setString(0, parameterType);
        query.setString(1, parameterKey);
        query.setString(2, website);
        return (OssAdminParameter) query.uniqueResult();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.OssAdminParameterDao#selectProvinceList(java .lang.String)
     */
    @Override
    public List<OssAdminParameter> selectProvinceList(String website) {
        Query query =
                getCurrentSession().createQuery(
                        "from OssAdminParameter where parameterType=? and website=? order by parameterOrder");
        query.setString(0, OssAdminParameterService.PARAM_TYPE_PROVINCE);
        query.setString(1, website);
        return query.list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.OssAdminParameterDao#selectNextLevelAreaList (java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<OssAdminParameter> selectNextLevelAreaList(String parameterType, String parameterKey, String website) {
        Criteria criteria = getCurrentSession().createCriteria(OssAdminParameter.class);
        criteria.add(Restrictions.eq("parameterType", parameterType));
        criteria.add(Restrictions.eq("webSite", website));
        criteria.add(Expression.like("parameterKey", StringUtils.isEmpty(parameterKey) ? "" : parameterKey + "%"));
        criteria.addOrder(Order.asc("parameterOrder"));
        return criteria.list();
    }

    @Override
    public List<OssAdminParameter> listParameters(String parameterType, String status, String siteType) {
        Criteria crit = getCurrentSession().createCriteria(OssAdminParameter.class);
        crit.add(Restrictions.eq("parameterType", parameterType));
        crit.add(Restrictions.eq("webSite", siteType));
        crit.add(Restrictions.eq("status", TCUtil.iv(status)));
        crit.addOrder(Order.asc("parameterOrder"));
        return crit.list();
    }

    @Override
    public void deleteByParameterType(String parameterType) {
        String hql = "DELETE " + OssAdminParameter.class.getName() + " _p_ WHERE _p_.parameterType=:parameterType";
        Query query = getCurrentSession().createQuery(hql).setString("parameterType", parameterType);
        query.executeUpdate();
    }

    @Override
    public List<OssAdminParameter> listParameters(String parentParKey, String parameterType, String status,
            String siteType) {
        Criteria crit = getCurrentSession().createCriteria(OssAdminParameter.class);
        crit.add(Restrictions.eq("parentParKey", parentParKey));
        crit.add(Restrictions.eq("parameterType", parameterType));
        crit.add(Restrictions.eq("webSite", siteType));
        crit.add(Restrictions.eq("status", TCUtil.iv(status)));
        crit.addOrder(Order.asc("parameterOrder"));
        return crit.list();
    }

    @Override
    public OssAdminParameter getLikeValueWithTypeAndWibsite(String parameterValue, String type, String website) {
        // TODO Auto-generated method stub
        OssAdminParameter ossAdminParameter = null;
        String hql =
                " from OssAdminParameter where parameterValue like ? and parameterType = ? and webSite = ? and status = 1";
        List<OssAdminParameter> list =
                getCurrentSession().createQuery(hql).setString(0, "%" + parameterValue + "%").setString(1, type)
                        .setString(2, website).list();
        if ((null != list) && (list.size() != 0)) {
            ossAdminParameter = list.get(0);
        }
        return ossAdminParameter;
    }

    @Override
    public OssAdminParameter getAdminEmail(String email) {
        String hql = " from OssAdminParameter where parameterType=? and extend like ? and status = 1";
        Query query = getCurrentSession().createQuery(hql);
        query.setString(0, OssAdminParConst.COMPANY_EMAIL_TYPE);
        query.setString(1, email + "%");
        return (OssAdminParameter) query.uniqueResult();
    }

    public OssAdminParameter listParametersByTypeKeyParAndSite(String parentParKey, String parameterType,
            String parameterKey, String status, String siteType) {
        Criteria crit = getCurrentSession().createCriteria(OssAdminParameter.class);
        if(!StringUtils.isEmpty(parentParKey)){
        	crit.add(Restrictions.eq("parentParKey", parentParKey));
        }
        crit.add(Restrictions.eq("parameterType", parameterType));
        crit.add(Restrictions.eq("parameterKey", parameterKey));
        crit.add(Restrictions.eq("webSite", siteType));
        crit.add(Restrictions.eq("status", TCUtil.iv(status)));
        crit.addOrder(Order.asc("parameterOrder"));
        return (OssAdminParameter) crit.uniqueResult();
    }
}
