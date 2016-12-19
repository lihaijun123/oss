package org.springside.components.acegi;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springside.components.acegi.resource.RoleScope;

import com.focustech.oss2008.acegi.OssConfigAttribute;
import com.focustech.oss2008.acegi.SecurityConfig;
import com.focustech.oss2008.service.impl.ScopeAuthorityHelper;

public class AuthenticationHelper {
    /**
     * Set of characters that qualify as parameter separators, indicating that a parameter name in a SQL String has
     * ended.
     */
    private static final char[] PARAMETER_SEPARATORS =
            new char[]{'"', '\'', ':', '&', ',', ';', '(', ')', '|', '=', '+', '-', '*', '%', '/', '\\', '<', '>', '^'};

    /**
     * 由GrantedAuthority Collection 轉為 GrantedAuthority 數組
     *
     * @param auths
     * @return
     */
    public static GrantedAuthority[] convertToGrantedAuthority(Collection<GrantedAuthorityImpl> auths) {
        return (GrantedAuthority[]) auths.toArray(new GrantedAuthority[auths.size()]);
    }

    /**
     * 把Bean中的某個屬性的值轉化為GrantedAuthority
     *
     * @param list Collection 一組Bean
     * @param propertyName 屬性名
     * @return GrantedAuthority[] GrantedAuthority數組
     */
    @SuppressWarnings("unchecked")
    public static GrantedAuthority[] convertToGrantedAuthority(Collection list, String propertyName) {
        Set<GrantedAuthorityImpl> authorities = new HashSet<GrantedAuthorityImpl>();
        try {
            for (Iterator iter = list.iterator(); iter.hasNext();) {
                String authority = (String) BeanUtils.getProperty(iter.next(), propertyName);
                authorities.add(new GrantedAuthorityImpl(authority));
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return convertToGrantedAuthority(authorities);
    }

    /**
     * 把Bean中的某個屬性的值轉化為scope
     *
     * @param list Collection 一組Bean
     * @param rolePropertyName 角色屬性名
     * @param socpPropertyName 範圍配置屬性名
     * @return String[] role/scope的Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, OssConfigAttribute> convertToRoleScopeMap(Collection<RoleScope> list,
            String rolePropertyName, String socpPropertyName, String attributeTypePropertyName) {
        Map<String, OssConfigAttribute> scopes = new HashMap<String, OssConfigAttribute>();
        try {
            for (RoleScope roleScope : list) {
                String role = (String) BeanUtils.getProperty(roleScope, rolePropertyName);
                String scope = (String) BeanUtils.getProperty(roleScope, socpPropertyName);
                int attributeType = Integer.parseInt(BeanUtils.getProperty(roleScope, attributeTypePropertyName));
                scopes.put(role, createConfigAttribute(role, scope, attributeType));
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return scopes;
    }

    public static OssConfigAttribute createConfigAttribute(String role, String scope, int attributeType) {
        Set<String> namedParameters = new HashSet<String>();
        if (StringUtils.isNotEmpty(scope) && ScopeAuthorityHelper.isSqlScope(scope)) {
            String sql = ScopeAuthorityHelper.analysisSql(scope);
            char[] statement = sql.toCharArray();
            boolean withinQuotes = false;
            char currentQuote = '-';
            int i = 0;
            while (i < statement.length) {
                char c = statement[i];
                if (withinQuotes) {
                    if (c == currentQuote) {
                        withinQuotes = false;
                        currentQuote = '-';
                    }
                }
                else {
                    if (c == '"' || c == '\'') {
                        withinQuotes = true;
                        currentQuote = c;
                    }
                    else {
                        if (c == ':' || c == '&') {
                            int j = i + 1;
                            while (j < statement.length && !isParameterSeparator(statement[j])) {
                                j++;
                            }
                            if (j - i > 1) {
                                String parameter = sql.substring(i + 1, j);
                                if (!namedParameters.contains(parameter)) {
                                    namedParameters.add(parameter);
                                }
                            }
                            i = j - 1;
                        }
                    }
                }
                i++;
            }
        }
        return new SecurityConfig(role, scope, namedParameters, attributeType);
    }

    /**
     * Determine whether a parameter name ends at the current position, that is, whether the given character qualifies
     * as a separator.
     */
    private static boolean isParameterSeparator(char c) {
        if (Character.isWhitespace(c)) {
            return true;
        }
        for (int i = 0; i < PARAMETER_SEPARATORS.length; i++) {
            if (c == PARAMETER_SEPARATORS[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把權限組轉為 ConfigAttributeDefinition.
     * <p>
     * 對于沒有聲明角色與之對應的資源，如果用戶成功登陸，則允許查看此類資源，如果用戶沒有成功登陸，則返回一個空的權限組，不允許其查看
     * </p>
     *
     * @param authorities 權限
     * @param isProtectAllResource 用戶是否成功登陸
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ConfigAttributeDefinition getCadByAuthorities(Collection<GrantedAuthority> authorities,
            Map<String, OssConfigAttribute> scope, boolean isAuthenticated) {
        // TODO:此處可以用投票器處理
        if (authorities == null || authorities.size() == 0) {
            if (isAuthenticated) {
                return null;
            }
            else {
                return new ConfigAttributeDefinition();
            }
        }
        ConfigAttributeDefinition configDefinition = new ConfigAttributeDefinition();
        for (Iterator iter = authorities.iterator(); iter.hasNext();) {
            GrantedAuthority authority = (GrantedAuthority) iter.next();
            configDefinition.addConfigAttribute(scope.get(authority));
        }
        return configDefinition;
    }
}
