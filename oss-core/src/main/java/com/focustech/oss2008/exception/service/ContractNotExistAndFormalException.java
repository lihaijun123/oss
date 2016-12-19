package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


/**
 * <li>對不存在合同並且不為正式合同進行操作異常</li>
 */
public class ContractNotExistAndFormalException extends OssRollbackCheckedException {

    /**
	 *
	 */
    private static final long serialVersionUID = -1762202332897069949L;

    /***/
    public ContractNotExistAndFormalException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.UNCONTRACT_OR_INFORMAL_NOPERATION_EXCEPTION));
    }

    public ContractNotExistAndFormalException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public ContractNotExistAndFormalException(Throwable cause) {
        super(cause);
    }

    public ContractNotExistAndFormalException(String message, Throwable cause) {
        super(message, cause);
    }
}
