package com.focustech.table.exception;

/**
 * <li>基本的運行期異常</li>
 *
 * @author MagicYang 2007-1-18 下午04:05:59 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public class BaseUncheckedException extends RuntimeException {

    private static final long serialVersionUID = -6361740323881293965L;

    /**
     * @param message
     */
    public BaseUncheckedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BaseUncheckedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BaseUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
