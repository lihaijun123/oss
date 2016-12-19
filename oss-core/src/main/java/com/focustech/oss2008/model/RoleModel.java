package com.focustech.oss2008.model;

import java.io.Serializable;

/**
 * <li>角色、資源對應的取值範圍</li>
 *
 * @author yangpeng 2008-6-23 下午02:40:31
 */
public class RoleModel implements Serializable {
    private static final long serialVersionUID = 6279080925253243727L;
    private Long recordId;
    private Long roleId;
    private String model;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
