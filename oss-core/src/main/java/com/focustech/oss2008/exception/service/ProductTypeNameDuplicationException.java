package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssUncheckedException;


public class ProductTypeNameDuplicationException extends OssUncheckedException {
    /***/
    private static final long serialVersionUID = 1099070884446188223L;

    public ProductTypeNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.PRODUCT_TYPE_HASEXIET_EXCEPTION));
    }
}
