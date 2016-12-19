package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


/**
 * <li>合同狀態已改變異常</li>
 */
public class ContractStateHasChangedException extends OssRollbackCheckedException {
    /***/
    private static final long serialVersionUID = -361617407924723422L;

    public ContractStateHasChangedException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CONTRACTSTATE_HASCHANGED_EXCEPTION));
    }

    public ContractStateHasChangedException(String message) {
        super(message);
    }

    public ContractStateHasChangedException(Throwable cause) {
        super(cause);
    }

    public ContractStateHasChangedException(String message, Throwable cause) {
        super(message, cause);
    }
}
