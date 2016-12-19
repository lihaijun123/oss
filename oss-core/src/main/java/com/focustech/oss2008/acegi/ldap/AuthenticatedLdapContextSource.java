package com.focustech.oss2008.acegi.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * <li></li>
 *
 * @author yangpeng 2008-12-11 上午10:52:25
 */
public class AuthenticatedLdapContextSource extends LdapContextSource implements AuthenticatedLdap {
    public DirContext getDirContext(final String principal, final String credentials) {
        final Hashtable<String, String> environment = (Hashtable) getAnonymousEnv().clone();
        environment.put(Context.SECURITY_PRINCIPAL, principal);
        environment.put(Context.SECURITY_CREDENTIALS, credentials);
        environment.remove("com.sun.jndi.ldap.connect.pool"); // remove this since we're modifying principal
        try {
            return getDirContextInstance(environment);
        }
        catch (final NamingException e) {
            throw new DataAccessResourceFailureException("Unable to create DirContext");
        }
    }
}
