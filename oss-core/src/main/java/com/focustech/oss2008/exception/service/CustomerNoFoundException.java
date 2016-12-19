package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class CustomerNoFoundException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 7235267170844504635L;

    public CustomerNoFoundException() {
        super(MessageUtils.getExceptionValue(MessageUtils
                .getExceptionValue(ExceptionConstants.UNCUSTOMER_OPERATION_EXCEPTION)));
    }

    public CustomerNoFoundException(String message) {
        super(message);
    }

    public CustomerNoFoundException(Throwable cause) {
        super(cause);
    }

    public CustomerNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
