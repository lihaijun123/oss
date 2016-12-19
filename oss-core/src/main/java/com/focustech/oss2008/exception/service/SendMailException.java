package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>發送郵件異常</li>
 */
public class SendMailException extends OssCheckedException {
    /**
	 *
	 */
    private static final long serialVersionUID = 5714242396594904674L;

    public SendMailException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.UNCONTRACT_OPERATION_EXCEPTION));
    }

    public SendMailException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public SendMailException(Throwable cause) {
        super(cause);
    }

    public SendMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
