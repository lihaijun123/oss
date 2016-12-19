package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.exception.OssCheckedException;


public class SignDateCompareException extends OssCheckedException {
    /**
	 *
	 */
    private static final long serialVersionUID = 3394700121530330187L;

    /**
     * 時間比較異常,驗證 簽約時間
     */
    public SignDateCompareException() {
    }

    public SignDateCompareException(String message) {
        super(MessageUtils.getExceptionValue(message));
    }

    public SignDateCompareException(Throwable cause) {
        super(cause);
    }

    public SignDateCompareException(String message, Throwable cause) {
        super(message, cause);
    }
}
