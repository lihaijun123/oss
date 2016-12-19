package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class DublicationMemberException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 7235267170844504635L;

    public DublicationMemberException() {
        super(MessageUtils.getExceptionValue(MessageUtils
                .getExceptionValue(ExceptionConstants.MEMBER_HASEXIET_EXCEPTION)));
    }

    public DublicationMemberException(String message) {
        super(message);
    }

    public DublicationMemberException(Throwable cause) {
        super(cause);
    }

    public DublicationMemberException(String message, Throwable cause) {
        super(message, cause);
    }
}
