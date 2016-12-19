package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class ResourceBookingInvalidationException extends OssRollbackCheckedException {
    /**
     * 預定資源失效異常
     */
    private static final long serialVersionUID = -281647140708304266L;

    public ResourceBookingInvalidationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.RESOURCEBOOKING_INVALIDATION_EXCEPTION));
    }

    public ResourceBookingInvalidationException(String message) {
        super(message);
    }

    public ResourceBookingInvalidationException(Throwable cause) {
        super(cause);
    }

    public ResourceBookingInvalidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
