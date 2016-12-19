package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class ProductTypeDisabledException extends OssCheckedException {
    /**
	 *
	 */
    private static final long serialVersionUID = -1817077070671436335L;

    public ProductTypeDisabledException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.PRODUCT_TYPE_DISABLED_EXCEPTION));
    }

    public ProductTypeDisabledException(String message) {
        super(message);
    }

    public ProductTypeDisabledException(Throwable cause) {
        super(cause);
    }

    public ProductTypeDisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
