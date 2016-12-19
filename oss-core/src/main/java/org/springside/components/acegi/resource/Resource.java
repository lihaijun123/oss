package org.springside.components.acegi.resource;

import java.util.HashMap;
import java.util.Map;

import org.acegisecurity.GrantedAuthority;
import org.springframework.util.Assert;

import com.focustech.oss2008.acegi.OssConfigAttribute;
import com.focustech.oss2008.service.ResourceService;

/**
 * ResourceDetails的實現類 resString 資源串， 如Url資源串 /admin/index.jsp, Method資源串 com.abc.service.userManager.save 等 resType
 * 資源類型，如URL, METHOD 等 authorities 該資源所擁有的權限
 *
 * @author cac
 */
public class Resource implements ResourceDetails {
    private static final long serialVersionUID = 4640497640533200574L;
    public static final String RESOURCE_TYPE_URL = ResourceService.RESOURCE_TYPE_URL;
    public static final String RESOURCE_TYPE_METHOD = ResourceService.RESOURCE_TYPE_METHOD;
    public static final String RESOURCE_TYPE_MENU = ResourceService.RESOURCE_TYPE_MENU;
    private String resString;
    private String resType;
    private GrantedAuthority[] authorities;
    private Map<String, OssConfigAttribute> scope = new HashMap<String, OssConfigAttribute>();

    public Resource(String resString, String resType, GrantedAuthority[] authorities,
            Map<String, OssConfigAttribute> roleScopes) {
        Assert.notNull(resString, "Cannot pass null or empty values to resource string");
        Assert.notNull(resType, "Cannot pass null or empty values to resource type");
        this.resString = resString;
        this.resType = resType;
        setAuthorities(authorities);
        setScope(roleScopes);
    }

    @Override
    public boolean equals(Object rhs) {
        if (!(rhs instanceof Resource))
            return false;
        Resource resauth = (Resource) rhs;
        if (!getResString().equals(resauth.getResString()))
            return false;
        if (!getResType().equals(resauth.getResType()))
            return false;
        if (resauth.getAuthorities().length != getAuthorities().length)
            return false;
        for (int i = 0; i < getAuthorities().length; i++) {
            if (!getAuthorities()[i].equals(resauth.getAuthorities()[i]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int code = 168;
        if (getAuthorities() != null) {
            for (int i = 0; i < getAuthorities().length; i++)
                code *= getAuthorities()[i].hashCode() % 7;
        }
        if (getResString() != null)
            code *= getResString().hashCode() % 7;
        return code;
    }

    public String getResString() {
        return resString;
    }

    public void setResString(String resString) {
        this.resString = resString;
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public void setAuthorities(GrantedAuthority[] authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
        for (int i = 0; i < authorities.length; i++) {
            Assert.notNull(authorities[i], "Granted authority element " + i
                    + " is null - GrantedAuthority[] cannot contain any null elements");
        }
        this.authorities = authorities;
    }

    public Map<String, OssConfigAttribute> getScope() {
        return scope;
    }

    public void setScope(Map<String, OssConfigAttribute> scope) {
        this.scope = scope;
    }
}
