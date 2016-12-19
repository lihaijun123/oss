package com.focustech.oss2008.exception.service;

import com.focustech.oss2008.exception.OssCheckedException;

/**
 * <li></li>
 *
 * @author yangpeng 2008-11-2 下午09:45:43
 */
public class MailSendErrorException extends OssCheckedException {
    /***/
    private static final long serialVersionUID = -2876118669996345418L;

    public MailSendErrorException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MailSendErrorException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public MailSendErrorException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public MailSendErrorException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
