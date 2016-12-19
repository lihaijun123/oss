package org.springside.components.acegi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springside.components.acegi.AuthenticationHelper;
import org.springside.components.acegi.resource.Resource;
import org.springside.components.acegi.resource.RoleScope;

import com.focustech.oss2008.acegi.OssConfigAttribute;

/**
 * 通過數據庫方式獲取User 和 Resource 實例
 *
 * @author cac
 */
public class DaoAuthenticationService extends JdbcDaoSupport implements AuthenticationService {
    // 獲取用戶名，密碼，狀態(name,passwd,status)
    private String loadUsersQuery;
    // 獲取相應用戶名下的所有權限(role.name)
    private String authoritiesByUsernameQuery;
    // 獲取所有資源的資源串和資源類型(res_string, res_type)
    private String loadResourcesQuery;
    // 獲取相應資源下的所有權限(role.name)
    private String authoritiesByResourceQuery;
    private String rolePrefix = "";
    private String rolePropertyName = "";
    private String scopePropertyName = "";
    private String attributeTypePropertyName = "";

	/*
     * 獲取所有資源實例 (non-Javadoc)
     * @see org.springside.components.acegi.service.AuthenticationService#getResources()
     */
    @SuppressWarnings("unchecked")
    public List<Resource> getResources() {
        List resources = new LoadResourcesMapping(getDataSource()).execute();
        List<Resource> authResources = new ArrayList<Resource>();
        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            Resource resc = (Resource) iter.next();
            String[] params = {resc.getResString(), resc.getResString()};
            List<RoleScope> roleScopeList = new AuthoritiesByResourcMapping(getDataSource()).execute(params);
            GrantedAuthority[] arrayAuths =
                    AuthenticationHelper.convertToGrantedAuthority(roleScopeList, getRolePropertyName());
            Map<String, OssConfigAttribute> scopes =
                    AuthenticationHelper.convertToRoleScopeMap(roleScopeList, getRolePropertyName(),
                            getScopePropertyName(), getAttributeTypePropertyName());
            authResources.add(new Resource(resc.getResString(), resc.getResType(), arrayAuths, scopes));
        }
        return authResources;
    }

    /*
     * 獲取所有用戶實例 (non-Javadoc)
     * @see org.springside.components.acegi.service.AuthenticationService#getUsers()
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        List users = new LoadUsersMapping(getDataSource()).execute();
        List<User> authUsers = new ArrayList<User>();
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            UserDetails user = (UserDetails) iter.next();
            List<GrantedAuthorityImpl> auths =
                    new AuthoritiesByUsernameMapping(getDataSource()).execute(user.getUsername());
            GrantedAuthority[] arrayAuths = AuthenticationHelper.convertToGrantedAuthority(auths);
            authUsers.add(new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
                    arrayAuths));
        }
        return authUsers;
    }
    /**
     * Query to look up users.
     */
    protected class LoadUsersMapping extends MappingSqlQuery {
        protected LoadUsersMapping(DataSource ds) {
            super(ds, loadUsersQuery);
            compile();
        }

        protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
            String username = rs.getString(1);
            String password = rs.getString(2);
            boolean enabled = rs.getBoolean(3);
            UserDetails user =
                    new User(username, password, enabled, true, true, true,
                            new GrantedAuthority[]{new GrantedAuthorityImpl("HOLDER")});
            return user;
        }
    }
    /**
     * Query object to look up a user's authorities.
     */
    protected class AuthoritiesByUsernameMapping extends MappingSqlQuery {
        protected AuthoritiesByUsernameMapping(DataSource ds) {
            super(ds, authoritiesByUsernameQuery);
            declareParameter(new SqlParameter(Types.VARCHAR));
            compile();
        }

        protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
            String roleName = rolePrefix + rs.getString(1);
            GrantedAuthorityImpl authority = new GrantedAuthorityImpl(roleName);
            return authority;
        }
    }
    /**
     * Query to look up resources.
     */
    protected class LoadResourcesMapping extends MappingSqlQuery {
        protected LoadResourcesMapping(DataSource ds) {
            super(ds, loadResourcesQuery);
            compile();
        }

        protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
            String resString = rs.getString(1);
            String resType = rs.getString(2);
            Resource resource =
                    new Resource(resString, resType, new GrantedAuthority[]{new GrantedAuthorityImpl("HOLDER")},
                            new HashMap<String, OssConfigAttribute>());
            return resource;
        }
    }
    /**
     * Query object to look up a resource's authorities.
     */
    protected class AuthoritiesByResourcMapping extends MappingSqlQuery {
        protected AuthoritiesByResourcMapping(DataSource ds) {
            super(ds, authoritiesByResourceQuery);
            SqlParameter[] params = {new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR)};
            setParameters(params);
            compile();
        }

        protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
            String roleName = rolePrefix + rs.getString(1);
            GrantedAuthorityImpl authority = new GrantedAuthorityImpl(roleName);
            RoleScope roleScope =
                    new RoleScope(authority, StringUtils.isEmpty(rs.getString(2)) ? "" : rs.getString(2), rs.getInt(3));
            return roleScope;
        }
    }

    public void setLoadUsersQuery(String loadUsersQuery) {
        this.loadUsersQuery = loadUsersQuery;
    }

    public void setAuthoritiesByUsernameQuery(String authoritiesByUsernameQuery) {
        this.authoritiesByUsernameQuery = authoritiesByUsernameQuery;
    }

    public void setAuthoritiesByResourceQuery(String authoritiesByResourceQuery) {
        this.authoritiesByResourceQuery = authoritiesByResourceQuery;
    }

    public void setLoadResourcesQuery(String loadResourcesQuery) {
        this.loadResourcesQuery = loadResourcesQuery;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    public String getRolePropertyName() {
        return rolePropertyName;
    }

    public void setRolePropertyName(String rolePropertyName) {
        this.rolePropertyName = rolePropertyName;
    }

    public String getScopePropertyName() {
        return scopePropertyName;
    }

    public void setScopePropertyName(String scopePropertyName) {
        this.scopePropertyName = scopePropertyName;
    }

    public String getAttributeTypePropertyName() {
        return attributeTypePropertyName;
    }

    public void setAttributeTypePropertyName(String attributeTypePropertyName) {
        this.attributeTypePropertyName = attributeTypePropertyName;
    }
}
