package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class ResourceNameDuplicationException extends OssCheckedException {
    private static final long serialVersionUID = 7343288810742531708L;

    public ResourceNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
    }

    public ResourceNameDuplicationException(String message) {
        super(message);
    }

    public ResourceNameDuplicationException(Throwable cause) {
        super(cause);
    }

    public ResourceNameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
