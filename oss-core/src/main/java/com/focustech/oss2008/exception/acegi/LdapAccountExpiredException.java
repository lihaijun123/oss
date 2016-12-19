package com.focustech.oss2008.exception.acegi;

import org.acegisecurity.AuthenticationException;

/**
 * <li>LDAP域帳戶過期異常</li>
 *
 * @author yangpeng 2008-12-22 上午10:36:29
 */
public class LdapAccountExpiredException extends AuthenticationException {
    private static final long serialVersionUID = -5447024523415859457L;
    private long remainDay = 0;

    public LdapAccountExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public LdapAccountExpiredException(String msg) {
        super(msg);
    }

    public LdapAccountExpiredException(long remainDay, String msg, Throwable t) {
        super(msg, t);
        this.remainDay = remainDay;
    }

    public LdapAccountExpiredException(long remainDay, String msg) {
        super(msg);
        this.remainDay = remainDay;
    }

    public long getRemainDay() {
        return remainDay;
    }
}
