package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.exception.OssCheckedException;


public class DateCompareException extends OssCheckedException {
    /**
     * 時間比較異常
     */
    private static final long serialVersionUID = 2593248323371139653L;

    public DateCompareException() {
    }

    public DateCompareException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public DateCompareException(Throwable cause) {
        super(cause);
    }

    public DateCompareException(String message, Throwable cause) {
        super(message, cause);
    }
}
