package com.focustech.oss2008.exception.acegi;

import org.acegisecurity.AuthenticationException;

/**
 * <li></li>
 *
 * @author yangpeng 2008-12-15 下午02:33:45
 */
public class TooSimpleLdapPasswordException extends AuthenticationException {
    private static final long serialVersionUID = 4874607451741501633L;

    /**
     * @param msg
     */
    public TooSimpleLdapPasswordException(String msg) {
        super(msg);
    }

    /**
     * @param msg
     * @param t
     */
    public TooSimpleLdapPasswordException(String msg, Throwable t) {
        super(msg, t);
    }
}
