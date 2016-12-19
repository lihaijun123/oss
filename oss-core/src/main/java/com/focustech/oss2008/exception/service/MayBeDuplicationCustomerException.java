package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class MayBeDuplicationCustomerException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 7532806878541044086L;

    public MayBeDuplicationCustomerException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CUSTOMER_HAS_CROSS));
    }

    public MayBeDuplicationCustomerException(String message) {
        super(message);
    }

    public MayBeDuplicationCustomerException(Throwable cause) {
        super(cause);
    }

    public MayBeDuplicationCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
