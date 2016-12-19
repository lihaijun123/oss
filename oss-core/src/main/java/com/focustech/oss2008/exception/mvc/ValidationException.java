package com.focustech.oss2008.exception.mvc;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssUncheckedException;


/**
 * <li>mvc驗證異常</li>
 *
 * @author yangpeng 2008-4-23 下午05:08:06
 */
public class ValidationException extends OssUncheckedException {
    private static final long serialVersionUID = 5647975957120946473L;

    /**
	 *
	 */
    public ValidationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.VALIDATE_GETVALUE_EXCEPTION));
    }

    /**
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
