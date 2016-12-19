package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class AgentBalanceNotEnoughException extends OssRollbackCheckedException {
    /** 代理商帳戶余額不足! */
    private static final long serialVersionUID = -3616174042422441230L;

    public AgentBalanceNotEnoughException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.AGENTBALANCE_NOTENOUGH_EXCEPTION));
    }

    public AgentBalanceNotEnoughException(String message) {
        super(message);
    }

    public AgentBalanceNotEnoughException(Throwable cause) {
        super(cause);
    }

    public AgentBalanceNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
