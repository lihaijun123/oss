package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class DuplicationCountLimitException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 7532806878541044086L;

    public DuplicationCountLimitException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.COUNT_LIMIT_HASEXIET_EXCEPTION));
    }

    public DuplicationCountLimitException(String message) {
        super(message);
    }

    public DuplicationCountLimitException(Throwable cause) {
        super(cause);
    }

    public DuplicationCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
