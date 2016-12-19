package com.focustech.oss2008.exception.mvc;

import com.focustech.oss2008.exception.OssUncheckedException;

/**
 * <li>對象不存在錯誤</li>
 *
 * @author yangpeng 2008-6-25 下午03:44:22
 */
public class NoSuchObjectException extends OssUncheckedException {
    /***/
    private static final long serialVersionUID = -4145595208442613828L;

    /**
	 *
	 */
    public NoSuchObjectException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public NoSuchObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public NoSuchObjectException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NoSuchObjectException(Throwable cause) {
        super(cause);
    }
}
