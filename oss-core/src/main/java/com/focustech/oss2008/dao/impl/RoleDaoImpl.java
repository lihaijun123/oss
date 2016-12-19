package com.focustech.oss2008.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.RoleDao;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.RoleResource;

@Repository
public class RoleDaoImpl extends OssHibernateDaoSupport<OssAdminRole> implements RoleDao<OssAdminRole> {
    public void insertRole(OssAdminRole role) {
        // role.setRoleId(getNewRoleId());
        getCurrentSession().save(role);
    }

    @SuppressWarnings("unchecked")
    public List<OssAdminRole> selectAllRoles() {
        return getCurrentSession().createQuery("from OssAdminRole").list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#selectAllUsefulRoles()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminRole> selectAllUsefulRoles() {
        return getCurrentSession().createQuery("from OssAdminRole where active=?").setString(0,
                Constants.ACTIVE_TYPE_ALLOW).list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#selectRoleByName(java.lang.String)
     */
    public OssAdminRole selectRoleByName(String roleName) {
        return (OssAdminRole) getCurrentSession().createQuery("from OssAdminRole where roleName=?").setString(0,
                roleName).uniqueResult();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#selectRoleByNameAndId(java.lang.String, java.lang.Long)
     */
    public OssAdminRole selectRoleByNameAndId(String roleName, Long roleId) {
        Query query = getCurrentSession().createQuery("from OssAdminRole where roleName=? and roleId!=?");
        query.setString(0, roleName);
        query.setLong(1, roleId);
        return (OssAdminRole) query.uniqueResult();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#getResourceByRole(java.lang.Long)
     */
    @Deprecated
    public Set<OssAdminResource> getResourceByRole(Long roleId) {
        Set<RoleResource> roleResourceSet = select(roleId).getResources();
        Set<OssAdminResource> resourceSet = new HashSet<OssAdminResource>();
        for (RoleResource temRoleResource : roleResourceSet) {
            resourceSet.add(temRoleResource.getResource());
        }
        return resourceSet;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#getModelByRole(java.lang.Long)
     */
    public List<String> getModelByRole(Long roleId) {
        String sql = "select MODEL from OSS_ADMIN_ROLE_MODEL where ROLE_ID = ?";
        List<Map<String, Object>> result = super.getJdbcTemplate().queryForList(sql, roleId);
        List<String> models = new ArrayList<String>();
        for (int i = 0; null != result && i < result.size(); i++) {
            String model = result.get(i).get("MODEL") == null ? null : result.get(i).get("MODEL").toString();
            if (null != model)
                models.add(model);
        }
        return models;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#getQueueByRole(java.lang.Long)
     */
    public List<Map<String, Object>> getQueueByRole(Long roleId) {
        String sql = "select * from sys_roltsktbl t where t.rtt_rolno = ?";
        return getJdbcTemplate().queryForList(sql, roleId.toString());
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.RoleDao#insertRoleQueue(java.lang.Long, java.lang.String)
     */
    public int insertRoleQueue(Long roleId, String queueId) {
        String sql = "insert into sys_roltsktbl values (?,?)";
        return getJdbcTemplate().update(sql, roleId.toString(), queueId);
    }

	@Override
	public long getNewUserId() {
		String sql = "SELECT max(role_id) from oss_admin_role";
        long id = TCUtil.lv(getCurrentSession().createSQLQuery(sql).uniqueResult().toString());
        return ++id;
	}
}
