package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;

import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.service.RoleService;

public class SingleRoleTypeEditor extends PropertyEditorSupport {
    private RoleService roleService;

    public SingleRoleTypeEditor(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        OssAdminRole role = roleService.getRole(Long.valueOf(text));
        super.setValue(role);
    }
}
