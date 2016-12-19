package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>用戶所擁有的數量已經超過了他的限制數量</li>
 */
public class CustomerUpperLimitException extends OssCheckedException {
    private static final long serialVersionUID = -1762202332897069949L;

    public CustomerUpperLimitException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CUSTOMER_OVER_UPPPER_LIMIT_EXCEPTION));
    }

    public CustomerUpperLimitException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public CustomerUpperLimitException(Throwable cause) {
        super(cause);
    }

    public CustomerUpperLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
