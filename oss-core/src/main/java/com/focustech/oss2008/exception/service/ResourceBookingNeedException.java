package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class ResourceBookingNeedException extends OssRollbackCheckedException {
    /**
     * 沒有可用的預定資源!
     */
    private static final long serialVersionUID = -743481090180063213L;

    public ResourceBookingNeedException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.RESOURCEBOOKING_NEED_EXCEPTION));
    }

    public ResourceBookingNeedException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public ResourceBookingNeedException(Throwable cause) {
        super(cause);
    }

    public ResourceBookingNeedException(String message, Throwable cause) {
        super(message, cause);
    }
}
