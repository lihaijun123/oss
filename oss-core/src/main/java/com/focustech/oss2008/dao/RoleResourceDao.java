package com.focustech.oss2008.dao;

import java.util.List;
import java.util.Map;

import com.focustech.oss2008.model.RoleResource;

/**
 * <li></li>
 *
 */
public interface RoleResourceDao<T> extends BaseHibernateDao<T> {
    public int selectScopeAuthority(String sql, Map<String, Object> parameters);

    public String selectRoleUrlScope(Long roleId, Long resourceId, String userId);

    // jibin
    public List<RoleResource> getRoleResourcesByRoleId(Long roleId);

    public RoleResource getByRoleAndResurce(Long roleId, Long resourceId);
}
