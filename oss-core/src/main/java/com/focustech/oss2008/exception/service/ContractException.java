package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>對不存在合同進行操作異常</li>
 */
public class ContractException extends OssCheckedException {
    /**
	 *
	 */
    private static final long serialVersionUID = -1542222148559944293L;

    /***/
    public ContractException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.UNCONTRACT_OPERATION_EXCEPTION));
    }

    public ContractException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public ContractException(Throwable cause) {
        super(cause);
    }

    public ContractException(String message, Throwable cause) {
        super(message, cause);
    }
}
