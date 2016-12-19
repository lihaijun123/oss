package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>登錄名重復異常</li>
 *
 * @author yangpeng 2008-4-23 上午09:29:10
 */
public class LoginNameDuplicationException extends OssCheckedException {
    private static final long serialVersionUID = -4681717718774166545L;

    /**
	 *
	 */
    public LoginNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.USER_HASEXIET_EXCEPTION));
    }

    /**
     * @param message
     */
    public LoginNameDuplicationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public LoginNameDuplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public LoginNameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
