package org.springside.components.acegi.resource;

import org.acegisecurity.GrantedAuthority;

/**
 * <li></li>
 *
 * @author yangpeng 2008-6-30 上午11:16:33
 */
public class RoleScope {
    private GrantedAuthority authority;
    private String scope;
    private int attributeType = 1;

    public RoleScope(GrantedAuthority authority, String scope, int attributeType) {
        this.authority = authority;
        this.scope = scope;
        this.attributeType = attributeType;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(GrantedAuthority authority) {
        this.authority = authority;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(int attributeType) {
        this.attributeType = attributeType;
    }
}
