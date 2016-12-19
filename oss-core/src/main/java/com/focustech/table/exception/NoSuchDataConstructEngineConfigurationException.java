package com.focustech.table.exception;

/**
 * <li>沒有指定的DataConstructEngine配置異常</li>
 *
 * @author MagicYang 2007-3-27 下午11:43:54 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public class NoSuchDataConstructEngineConfigurationException extends BaseUncheckedException {

    private static final long serialVersionUID = -5981457232191522996L;

    /**
     * @param message
     */
    public NoSuchDataConstructEngineConfigurationException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public NoSuchDataConstructEngineConfigurationException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public NoSuchDataConstructEngineConfigurationException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
