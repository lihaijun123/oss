package com.focustech.oss2008.exception.mvc;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssUncheckedException;


/**
 * <li>未登陸異常</li>
 *
 * @author yangpeng 2008-5-9 上午11:45:15
 */
public class UnSuccessfulLoginException extends OssUncheckedException {
    private static final long serialVersionUID = 4942904530501897118L;

    /**
	 *
	 */
    public UnSuccessfulLoginException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.LOGON_FAIL_EXCEPTION));
    }

    /**
     * @param message
     * @param cause
     */
    public UnSuccessfulLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UnSuccessfulLoginException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public UnSuccessfulLoginException(Throwable cause) {
        super(cause);
    }
}
