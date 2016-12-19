package com.focustech.oss2008.acegi;

import java.util.Set;

import com.focustech.oss2008.service.impl.ScopeAuthorityHelper;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-1 下午04:23:40
 */
public class SecurityConfig implements OssConfigAttribute {
    private static final long serialVersionUID = 1091125229585013084L;
    private String attribute;
    private String scope;
    private Set<String> parameterNames;
    private int attributeType = 1;// 默認為角色賦權限

    public SecurityConfig(String attribute, String scope, Set<String> parameterNames, int attributeType) {
        this.attribute = attribute;
        this.parameterNames = parameterNames;
        this.scope = scope;
        this.attributeType = attributeType;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.acegi.OssConfigAttribute#getParameterNames()
     */
    public Set<String> getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(Set<String> parameterNames) {
        this.parameterNames = parameterNames;
    }

    public boolean isJavaConfig() {
        return ScopeAuthorityHelper.isJavaScope(scope);
    }

    public boolean isSqlConfig() {
        return ScopeAuthorityHelper.isSqlScope(scope);
    }

    public boolean hasFullAuthory() {
        return ScopeAuthorityHelper.hasFullAuthority(scope);
    }

    public int getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(int attributeType) {
        this.attributeType = attributeType;
    }
}
