package com.focustech.oss2008.acegi.ldap;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.oss2008.exception.acegi.TooSimpleLdapPasswordException;

/**
 * <li></li>
 *
 * @author yangpeng 2008-12-12 上午10:52:06
 */
public class OssLdapAuthenticationProvider extends LdapAuthenticationProvider {
    private List<String> simplePasswordList = new ArrayList<String>();
    @Autowired
    private UserDetailsService userService;

    public OssLdapAuthenticationProvider(LdapAuthenticator authenticator, LdapAuthoritiesPopulator authoritiesPopulator) {
        super(authenticator, authoritiesPopulator);
    }

    public OssLdapAuthenticationProvider(LdapAuthenticator authenticator) {
        super(authenticator);
    }

    @Override
    protected UserDetails createUserDetails(LdapUserDetails ldapUser, String username, String password) {
        return userService.loadUserByUsername(username);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        checkIsSimplePassword(userDetails, authentication);
    }

    /**
     * 檢查用戶的密碼是否是初始或簡單密碼
     *
     * @throws TooSimpleLdapPasswordException 用戶登陸密碼處于簡單密碼列表中
     */
    protected void checkIsSimplePassword(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
        if (null != authentication && null != authentication.getCredentials())
            if (simplePasswordList.contains(authentication.getCredentials().toString())) {
                throw new TooSimpleLdapPasswordException("user password :" + authentication.getCredentials().toString()
                        + ",is too simple!");
            }
    }

    public List<String> getSimplePasswordList() {
        return simplePasswordList;
    }

    public void setSimplePasswordList(List<String> simplePasswordList) {
        this.simplePasswordList = simplePasswordList;
    }
}
