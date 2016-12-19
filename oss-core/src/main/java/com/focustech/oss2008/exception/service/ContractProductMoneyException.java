package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class ContractProductMoneyException extends OssRollbackCheckedException {
    /** 合同產品金額異常 */
    private static final long serialVersionUID = -8012279683443481079L;

    public ContractProductMoneyException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.CONTRACTPRODUCT_NOTEXIET_EXCEPTION));
    }

    public ContractProductMoneyException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public ContractProductMoneyException(Throwable cause) {
        super(cause);
    }

    public ContractProductMoneyException(String message, Throwable cause) {
        super(message, cause);
    }
}
