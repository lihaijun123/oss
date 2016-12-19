package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssUncheckedException;


public class ProductTagNameDuplicationException extends OssUncheckedException {
    public ProductTagNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.PRODUCT_TAG_HASEXIET_EXCEPTION));
    }
}
