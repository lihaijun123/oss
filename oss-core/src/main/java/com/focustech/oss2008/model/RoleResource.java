package com.focustech.oss2008.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * <li>角色、資源對應的取值範圍</li>
 *
 * @author yangpeng 2008-6-23 下午02:40:31
 */
public class RoleResource implements Serializable {
    private static final long serialVersionUID = 6279080925253243727L;
    private Long recordId;
    private String scope;
    private OssAdminResource resource;
    private OssAdminRole role;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public OssAdminResource getResource() {
        return resource;
    }

    public void setResource(OssAdminResource resource) {
        this.resource = resource;
    }

    public OssAdminRole getRole() {
        return role;
    }

    public void setRole(OssAdminRole role) {
        this.role = role;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public boolean isEqualsScope(String scope) {
        if (this.scope == scope)
            return true;
        else if (StringUtils.isEmpty(this.scope) && StringUtils.isEmpty(scope))
            return true;
        else if (this.scope == null || scope == null)
            return false;
        else if (this.scope.equals(scope))
            return true;
        else
            return false;
    }
}
