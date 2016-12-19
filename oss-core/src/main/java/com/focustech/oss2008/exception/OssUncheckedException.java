package com.focustech.oss2008.exception;

/**
 * <li>OSS 運行時異常,用于拋出系統錯誤</li>
 *
 * @author yangpeng 2008-4-23 上午09:24:20
 */
public class OssUncheckedException extends RuntimeException {
    private static final long serialVersionUID = 7952385271052856778L;

    /**
	 *
	 */
    public OssUncheckedException() {
    }

    /**
     * @param message
     */
    public OssUncheckedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public OssUncheckedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public OssUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
