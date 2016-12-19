package org.springside.components.acegi.resource;

import java.io.Serializable;
import java.util.Map;

import org.acegisecurity.GrantedAuthority;

import com.focustech.oss2008.acegi.OssConfigAttribute;

/**
 * 提供資源信息
 *
 * @author cac
 */
public interface ResourceDetails extends Serializable {
    /**
     * 資源串
     */
    public String getResString();

    /**
     * 資源類型,如URL,FUNCTION
     */
    public String getResType();

    /**
     * 返回屬于該resource的authorities
     */
    public GrantedAuthority[] getAuthorities();

    public void setAuthorities(GrantedAuthority[] authorities);

    public Map<String, OssConfigAttribute> getScope();

    public void setScope(Map<String, OssConfigAttribute> scope);
}
