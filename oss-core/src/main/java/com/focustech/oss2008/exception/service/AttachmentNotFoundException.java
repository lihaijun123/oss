package com.focustech.oss2008.exception.service;

import com.focustech.oss2008.exception.OssCheckedException;

/**
 * <li>附件不存在異常，用于業務處理</li>
 *
 * @author zhanghua 2008-6-11 上午09:22:51
 */
public class AttachmentNotFoundException extends OssCheckedException {
    private static final long serialVersionUID = -4887431503936279485L;

    /**
	 *
	 */
    public AttachmentNotFoundException() {
    }

    /**
     * @param message
     */
    public AttachmentNotFoundException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AttachmentNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public AttachmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
