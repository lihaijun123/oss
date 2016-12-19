package com.focustech.oss2008.exception.service;

import com.focustech.oss2008.exception.OssCheckedException;

/**
 * <li>需要回滾的checked異常父類</li>
 *
 * @author yangpeng 2008-7-2 下午03:20:17
 */
public class OssRollbackCheckedException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = 8032308843023262620L;

    /**
	 *
	 */
    public OssRollbackCheckedException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public OssRollbackCheckedException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public OssRollbackCheckedException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public OssRollbackCheckedException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
