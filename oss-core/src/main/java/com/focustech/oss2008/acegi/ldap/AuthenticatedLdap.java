package com.focustech.oss2008.acegi.ldap;

import javax.naming.directory.DirContext;

import org.springframework.ldap.core.support.BaseLdapPathContextSource;

/**
 * <li></li>
 *
 * @author yangpeng 2008-12-11 上午11:31:46
 */
public interface AuthenticatedLdap extends BaseLdapPathContextSource {
    public DirContext getDirContext(final String principal, final String credentials);
}
