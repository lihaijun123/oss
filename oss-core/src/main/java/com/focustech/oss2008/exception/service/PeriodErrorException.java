package com.focustech.oss2008.exception.service;

import com.focustech.oss2008.exception.OssCheckedException;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-4 上午09:53:35
 */
public class PeriodErrorException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 5627375855944313901L;

    /**
	 *
	 */
    public PeriodErrorException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public PeriodErrorException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public PeriodErrorException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public PeriodErrorException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
