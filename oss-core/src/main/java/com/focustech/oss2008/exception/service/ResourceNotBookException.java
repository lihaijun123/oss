package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssUncheckedException;


public class ResourceNotBookException extends OssUncheckedException {
    private static final long serialVersionUID = 7343288810742531799L;

    public ResourceNotBookException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.RESOURCE_NOT_BOOK_EXCEPTION));
    }

    public ResourceNotBookException(String message) {
        super(message);
    }

    public ResourceNotBookException(Throwable cause) {
        super(cause);
    }

    public ResourceNotBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
