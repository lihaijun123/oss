package com.focustech.oss2008.service;

import com.focustech.oss2008.model.RoleModel;

/**
 * <li>角色模塊管理Service</li>
 *
 * @author jibin 2008-11-18 上午09:58:11
 */
public interface RoleModelService {
    /**
     * 獲取當前角色模塊信息
     *
     * @param recordId
     * @return
     */
    RoleModel getRoleModel(Long recordId);

    /**
     * 新增角色模塊信息
     *
     * @param roleModel
     */
    void addRoleModel(RoleModel roleModel);

    /**
     * 修改角色模塊信息
     *
     * @param roleModel
     */
    void modifyRoleModel(RoleModel roleModel);

    /**
     * 刪除角色模塊信息
     *
     * @param recordId
     */
    void deleteRoleModel(Long recordId);
}
