package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class ContractReceiveMoneyUNCorrectException extends OssRollbackCheckedException {
    /**
     * 合同應收款金額異常
     */
    private static final long serialVersionUID = 5952552098365125037L;

    public ContractReceiveMoneyUNCorrectException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
    }

    public ContractReceiveMoneyUNCorrectException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public ContractReceiveMoneyUNCorrectException(Throwable cause) {
        super(cause);
    }

    public ContractReceiveMoneyUNCorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
