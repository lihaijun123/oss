package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class DuplicationCustomerException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 7532806878541044086L;

    public DuplicationCustomerException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CUSTOMER_HAS_CROSS));
    }

    public DuplicationCustomerException(String message) {
        super(message);
    }

    public DuplicationCustomerException(Throwable cause) {
        super(cause);
    }

    public DuplicationCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
