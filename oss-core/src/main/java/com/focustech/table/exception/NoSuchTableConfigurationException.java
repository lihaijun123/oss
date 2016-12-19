package com.focustech.table.exception;

/**
 * <li>沒有指定表格配置異常</li>
 *
 * @author MagicYang 2007-1-18 下午04:11:56 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public class NoSuchTableConfigurationException extends BaseUncheckedException {

    private static final long serialVersionUID = 7249480666836638432L;

    /**
     * @param message
     */
    public NoSuchTableConfigurationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NoSuchTableConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public NoSuchTableConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
