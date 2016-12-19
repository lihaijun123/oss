package com.focustech.oss2008.exception.service;

import com.focustech.oss2008.exception.OssUncheckedException;

/**
 * <li>LDAP服務中拋出的受控異常</li>
 *
 * @author yangpeng 2008-12-24 下午03:15:20
 */
public class LdapCheckedException extends OssUncheckedException {
    private static final long serialVersionUID = 1209581488039806512L;

    public LdapCheckedException() {
        super();
    }

    public LdapCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LdapCheckedException(String message) {
        super(message);
    }

    public LdapCheckedException(Throwable cause) {
        super(cause);
    }
}
