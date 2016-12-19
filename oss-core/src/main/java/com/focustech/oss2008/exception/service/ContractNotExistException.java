package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


public class ContractNotExistException extends OssCheckedException {
    /** 合同不存在異常 */
    private static final long serialVersionUID = -36161740354482340L;

    public ContractNotExistException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.UNCONTRACT_OPERATION_EXCEPTION));
    }

    public ContractNotExistException(String message) {
        super(message);
    }

    public ContractNotExistException(Throwable cause) {
        super(cause);
    }

    public ContractNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
