package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class RoleNameDuplicationException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = -3616174079247263110L;

    public RoleNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
    }

    public RoleNameDuplicationException(String message) {
        super(message);
    }

    public RoleNameDuplicationException(Throwable cause) {
        super(cause);
    }

    public RoleNameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
