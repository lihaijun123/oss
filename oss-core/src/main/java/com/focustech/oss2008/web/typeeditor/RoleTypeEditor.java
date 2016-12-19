package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.service.RoleService;

public class RoleTypeEditor extends PropertyEditorSupport {
    private RoleService roleService;

    public RoleTypeEditor(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Set<OssAdminRole> set = new HashSet<OssAdminRole>();
        // for (Iterator<OssAdminRoles> iter =
        // roleService.getAllRoles().iterator(); iter.hasNext();) {
        // OssAdminRoles role = (OssAdminRoles) iter.next();
        // set.add(role);
        // }
        if (text == null || "".equals(text)) {
            return;
        }
        set.add(roleService.getRole(Long.valueOf(text)));
        super.setValue(set);
    }
}
