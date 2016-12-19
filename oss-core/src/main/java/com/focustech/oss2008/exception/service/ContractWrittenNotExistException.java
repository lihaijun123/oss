package com.focustech.oss2008.exception.service;

/**
 * 書面合同不存在異常類 ContractWrittenNotExistException.java
 *
 * @author jibin
 */
public class ContractWrittenNotExistException extends OssRollbackCheckedException {

    private static final long serialVersionUID = -6009729236860138900L;

    public ContractWrittenNotExistException() {
        super();
    }

    public ContractWrittenNotExistException(String message) {
        super(message);
    }

    public ContractWrittenNotExistException(Throwable cause) {
        super(cause);
    }

    public ContractWrittenNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
