package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssUncheckedException;


public class ProductCategoryNameDuplicationException extends OssUncheckedException {
    public ProductCategoryNameDuplicationException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.PRODUCT_CATEGORY_HASEXIET_EXCEPTION));
    }
}
