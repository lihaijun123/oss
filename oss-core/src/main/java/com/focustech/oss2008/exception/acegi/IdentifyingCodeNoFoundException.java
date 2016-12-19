package com.focustech.oss2008.exception.acegi;

import org.acegisecurity.AuthenticationException;

/**
 * <li></li>
 *
 */
public class IdentifyingCodeNoFoundException extends AuthenticationException {
    private static final long serialVersionUID = -1572639660061689539L;

    /**
     * @param msg
     * @param t
     */
    public IdentifyingCodeNoFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * @param msg
     */
    public IdentifyingCodeNoFoundException(String msg) {
        super(msg);
    }
}
