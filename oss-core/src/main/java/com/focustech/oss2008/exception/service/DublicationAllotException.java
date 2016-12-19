package com.focustech.oss2008.exception.service;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.OssCheckedException;


/**
 * <li>對客戶進行了重復分配而導致的異常</li>
 *
 * @author taofucheng 2008-9-16 上午11:53:47
 */
public class DublicationAllotException extends OssCheckedException {
    private static final long serialVersionUID = -5027853600260101178L;

    public DublicationAllotException() {
        super(MessageUtils.getExceptionValue(ExceptionConstants.DUBLICATED_ALLOT_CUSTOMER_EXCEPTION));
    }

    public DublicationAllotException(String message) {
        super(message);
    }

    public DublicationAllotException(Throwable cause) {
        super(cause);
    }

    public DublicationAllotException(String message, Throwable cause) {
        super(message, cause);
    }
}
