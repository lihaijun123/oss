package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>變更合同操作異常</li>
 */
public class AlterContractException extends OssCheckedException {
    private static final long serialVersionUID = 8508428736486997145L;

    public AlterContractException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.UNCONTRACT_OPERATION_EXCEPTION));
    }

    public AlterContractException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public AlterContractException(Throwable cause) {
        super(cause);
    }

    public AlterContractException(String message, Throwable cause) {
        super(message, cause);
    }
}
