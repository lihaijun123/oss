package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.exception.OssCheckedException;


public class RelatedContractCheckException extends OssCheckedException {
    /**
     * 檢查 變更合同異常
     */
    private static final long serialVersionUID = 6682215695466517907L;

    public RelatedContractCheckException() {
    }

    public RelatedContractCheckException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public RelatedContractCheckException(Throwable cause) {
        super(cause);
    }

    public RelatedContractCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
