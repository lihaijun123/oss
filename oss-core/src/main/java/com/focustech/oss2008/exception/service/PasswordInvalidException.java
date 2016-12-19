package com.focustech.oss2008.exception.service;


/**
 * <li></li>
 *
 * @author yangpeng 2008-12-16 下午02:50:15
 */
public class PasswordInvalidException extends OssRollbackCheckedException {
    /**
	 *
	 */
    public PasswordInvalidException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public PasswordInvalidException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public PasswordInvalidException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public PasswordInvalidException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
