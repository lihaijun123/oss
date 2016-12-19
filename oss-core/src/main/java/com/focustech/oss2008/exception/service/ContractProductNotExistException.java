package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class ContractProductNotExistException extends OssRollbackCheckedException {
    /** 合同產品不存在異常 */
    private static final long serialVersionUID = -36161740323242340L;

    public ContractProductNotExistException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CONTRACTPRODUCT_NOTEXIET_EXCEPTION));
    }

    public ContractProductNotExistException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public ContractProductNotExistException(Throwable cause) {
        super(cause);
    }

    public ContractProductNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
