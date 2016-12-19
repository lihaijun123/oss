package com.focustech.oss2008.dao.impl;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.RoleModelDao;
import com.focustech.oss2008.model.RoleModel;

/**
 * <li>角色模塊管理</li>
 *
 * @author jibin 2008-11-18 上午09:43:12
 */
@Repository
public class RoleModelDaoImpl extends OssHibernateDaoSupport<RoleModel> implements RoleModelDao<RoleModel> {

    public int checkRoleModel(RoleModel roleModel) {
        String sql = "SELECT * FROM OSS_ADMIN_ROLE_MODEL T WHERE T.ROLE_ID=? AND T.MODEL=?";
        return getJdbcTemplate().queryForList(sql, roleModel.getRoleId(), roleModel.getModel()).size();
    }
}
