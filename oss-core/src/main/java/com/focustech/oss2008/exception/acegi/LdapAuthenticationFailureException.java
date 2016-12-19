package com.focustech.oss2008.exception.acegi;

import org.acegisecurity.AuthenticationException;

/**
 * <li>LDAP驗證失敗異常</li>
 *
 * @author yangpeng 2008-12-12 下午01:58:02
 */
public class LdapAuthenticationFailureException extends AuthenticationException {
    private static final long serialVersionUID = -6452050498105255055L;

    public LdapAuthenticationFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public LdapAuthenticationFailureException(String msg) {
        super(msg);
    }
}
