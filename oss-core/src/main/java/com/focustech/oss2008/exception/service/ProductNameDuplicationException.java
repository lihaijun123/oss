package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class ProductNameDuplicationException extends OssCheckedException {
    private static final long serialVersionUID = 8355693299246827809L;

    public ProductNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.PRODUCT_HASEXIET_EXCEPTION));
    }

    public ProductNameDuplicationException(String message) {
        super(message);
    }

    public ProductNameDuplicationException(Throwable cause) {
        super(cause);
    }

    public ProductNameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
