package com.focustech.oss2008.exception.service;

import com.focustech.oss2008.exception.OssCheckedException;

/**
 * <li>代理商合同進行操作異常</li>
 */
public class AgentContractException extends OssCheckedException {
    /**
	 *
	 */
    private static final long serialVersionUID = -1542222148559944293L;

    /***/
    public AgentContractException() {
        super("代理商目前不可以建美元合同！");
    }

    public AgentContractException(String message) {
        super(message);
    }

    public AgentContractException(Throwable cause) {
        super(cause);
    }

    public AgentContractException(String message, Throwable cause) {
        super(message, cause);
    }
}
