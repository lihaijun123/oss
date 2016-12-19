package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class ContractMoneyNotExistException extends OssRollbackCheckedException {
    /**
     * 合同總額未設定!
     */
    private static final long serialVersionUID = 7961459412637916863L;

    public ContractMoneyNotExistException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CONTRACTMONEY_NOTEXIET_EXCEPTION));
    }

    public ContractMoneyNotExistException(String message) {
        super(message);
    }

    public ContractMoneyNotExistException(Throwable cause) {
        super(cause);
    }

    public ContractMoneyNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
