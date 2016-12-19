package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class RelatedContractNotExistException extends OssCheckedException {
    /** 關聯合同不存在異常 */
    private static final long serialVersionUID = -36161740354482340L;

    public RelatedContractNotExistException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CONTRACT_RELATED_NOTEXIST_EXCEPTION));
    }

    public RelatedContractNotExistException(String message) {
        super(message);
    }

    public RelatedContractNotExistException(Throwable cause) {
        super(cause);
    }

    public RelatedContractNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
