package com.focustech.oss2008.dao;

import com.focustech.oss2008.model.RoleModel;

/**
 * <li>角色模塊管理</li>
 *
 * @author jibin 2008-11-18 上午09:42:23
 */
public interface RoleModelDao<T> extends BaseHibernateDao<T> {
    /**
     * 檢驗是否已有該記錄
     *
     * @param roleModel
     * @return 結果個數
     */
    int checkRoleModel(RoleModel roleModel);
}
