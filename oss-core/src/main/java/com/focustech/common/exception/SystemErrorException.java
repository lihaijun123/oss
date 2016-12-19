package com.focustech.common.exception;

/**
 * 系统错误类
 * 
 * @author geliang
 */
public class SystemErrorException extends RuntimeException {

    public SystemErrorException(String msg) {
        super(msg);
    }

    public SystemErrorException(Exception e) {
        super(e);
    }

    private static final long serialVersionUID = 6863919340008867446L;

}
