package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;


public class AgentAccountNotExistException extends OssRollbackCheckedException {
    /** 代理商帳戶不存在! */
    private static final long serialVersionUID = -3616165462441230L;

    public AgentAccountNotExistException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.AGENTACCOUNT_NOTEXIST_EXCEPTION));
    }

    public AgentAccountNotExistException(String message) {
        super(message);
    }

    public AgentAccountNotExistException(Throwable cause) {
        super(cause);
    }

    public AgentAccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
