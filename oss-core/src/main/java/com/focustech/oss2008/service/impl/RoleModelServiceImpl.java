package com.focustech.oss2008.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.RoleModelDao;
import com.focustech.oss2008.exception.OssUncheckedException;
import com.focustech.oss2008.model.RoleModel;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.RoleModelService;

/**
 * <li>角色模塊管理ServiceImpl</li>
 *
 * @author jibin 2008-11-18 上午09:58:42
 */
@Service
public class RoleModelServiceImpl extends AbstractServiceSupport implements RoleModelService {
    @Autowired
    private RoleModelDao<RoleModel> roleModelDao;

    public RoleModel getRoleModel(Long recordId) {
        return roleModelDao.select(recordId);
    }

    public void addRoleModel(RoleModel roleModel) {
        int i = roleModelDao.checkRoleModel(roleModel);
        if (i > 0) {
            throw new OssUncheckedException("該記錄已存在，不能插入！");
        }
        roleModelDao.insert(roleModel);
    }

    public void modifyRoleModel(RoleModel roleModel) {
        int i = roleModelDao.checkRoleModel(roleModel);
        if (i > 0) {
            throw new OssUncheckedException("該記錄已存在，不能修改！");
        }
        roleModelDao.update(roleModel);
    }

    public void deleteRoleModel(Long recordId) {
        roleModelDao.delete(recordId);
    }
}
