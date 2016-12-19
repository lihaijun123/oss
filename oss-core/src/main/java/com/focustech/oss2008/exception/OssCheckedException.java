package com.focustech.oss2008.exception;

/**
 * <li>OSS檢查異常，用于業務處理</li>
 *
 * @author yangpeng 2008-4-23 上午09:22:51
 */
public class OssCheckedException extends Exception {
    private static final long serialVersionUID = -4887431503936279485L;

    /**
	 *
	 */
    public OssCheckedException() {
    }

    /**
     * @param message
     */
    public OssCheckedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public OssCheckedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public OssCheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
