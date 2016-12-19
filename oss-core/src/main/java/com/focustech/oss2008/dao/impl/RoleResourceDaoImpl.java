package com.focustech.oss2008.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.ResourceDao;
import com.focustech.oss2008.dao.RoleResourceDao;
import com.focustech.oss2008.model.RoleResource;

/**
 * <li></li>
 *
 * @author yangpeng 2008-6-25 上午11:38:43
 */
@Repository
public class RoleResourceDaoImpl extends OssHibernateDaoSupport<RoleResource> implements RoleResourceDao<RoleResource> {
    public int selectScopeAuthority(String sql, Map<String, Object> parameters) {
        return getJdbcTemplate().queryForInt(sql, parameters);
    }

    public String selectRoleUrlScope(Long roleId, Long resourceId, String userId) {
/*        String sql =
                "SELECT role_id,DECODE (data_scope_formula,NULL, (SELECT a.data_scope_formula "
                        + "FROM oss_admin_role_resource a WHERE a.role_id = ? AND a.resource_id =?), data_scope_formula)"
                        + " data_scope_formula FROM oss_admin_role_resource WHERE role_id = ? AND resource_id = ?";*/
    	        String sql =
                "SELECT role_id,if(data_scope_formula is null, (SELECT a.data_scope_formula "
                        + "FROM oss_admin_role_resource a WHERE a.role_id = ? AND a.resource_id =?), data_scope_formula)"
                        + " data_scope_formula FROM oss_admin_role_resource WHERE role_id = ? AND resource_id = ?";
        Object result =
                getCurrentSession().createSQLQuery(sql).setLong(0, roleId).setLong(1, ResourceDao.PUBLIC_RESOURCE_ID)
                        .setLong(2, roleId).setLong(3, resourceId).uniqueResult();
        if (null != result) {
            String res = ((Object[]) result)[1] == null ? "" : String.valueOf(((Object[]) result)[1]);
            if (res == null)
                res = "";
            return res;
        }
        else {
            sql = "SELECT RESOURCE_ID FROM OSS_ADMIN_USER_RESOURCE WHERE USER_ID =? AND RESOURCE_ID =?";
            result = getCurrentSession().createSQLQuery(sql).setString(0, userId).setLong(1, resourceId).uniqueResult();
            if (null != result) {
                return "";
            }
            return null;
        }
    }

    // jibin
    @SuppressWarnings("unchecked")
    public List<RoleResource> getRoleResourcesByRoleId(Long roleId) {
        return getCurrentSession().createQuery(" from RoleResource where role.roleId=?").setLong(0, roleId).list();
    }

	@Override
	public RoleResource getByRoleAndResurce(Long roleId, Long resourceId) {
		return (RoleResource) getCurrentSession().createQuery(" from RoleResource where role.roleId=? and resource.resourceId=?").setLong(0, roleId).setLong(1, resourceId).uniqueResult();
	}
}
