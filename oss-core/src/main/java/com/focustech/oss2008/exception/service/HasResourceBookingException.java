package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>開放客戶時，發現有預定資源而不允許開放</li>
 */
public class HasResourceBookingException extends OssCheckedException {
    private static final long serialVersionUID = -1542222148559944293L;

    /***/
    public HasResourceBookingException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.UNOPEN_HAS_RESOURCE_BOOKING_EXCEPTION));
    }

    public HasResourceBookingException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public HasResourceBookingException(Throwable cause) {
        super(cause);
    }

    public HasResourceBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
