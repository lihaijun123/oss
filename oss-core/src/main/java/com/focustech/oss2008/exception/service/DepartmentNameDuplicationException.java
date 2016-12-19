package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class DepartmentNameDuplicationException extends OssCheckedException {

    /***/
    private static final long serialVersionUID = -8766204588436627821L;

    public DepartmentNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.ORGAN_HASEXIET_EXCEPTION));
    }

    public DepartmentNameDuplicationException(String message) {
        super(message);
    }

    public DepartmentNameDuplicationException(Throwable cause) {
        super(cause);
    }

    public DepartmentNameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
