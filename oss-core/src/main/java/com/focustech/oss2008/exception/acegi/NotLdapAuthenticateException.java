package com.focustech.oss2008.exception.acegi;

import org.acegisecurity.AuthenticationException;

/**
 * <li>不使用LDAP認證異常</li>
 *
 * @author yangpeng 2008-12-12 上午10:13:47
 */
public class NotLdapAuthenticateException extends AuthenticationException {
    private static final long serialVersionUID = 8923261512499229248L;

    /**
     * @param msg
     */
    public NotLdapAuthenticateException(String msg) {
        super(msg);
    }

    /**
     * @param msg
     * @param t
     */
    public NotLdapAuthenticateException(String msg, Throwable t) {
        super(msg, t);
    }
}
