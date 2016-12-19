package com.focustech.oss2008.web.adapter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.PropertyAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.FieldError;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ValidateConstants;
import com.focustech.oss2008.utils.GenericsUtils;


/**
 * <li></li>
 *
 * @author yangpeng 2008-7-7 下午02:30:55
 */
public class OssBindingErrorProcessor extends DefaultBindingErrorProcessor {
    protected static Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);

    @SuppressWarnings("unchecked")
    @Override
    public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
        // Create field error with the exceptions's code, e.g. "typeMismatch".
        String field = ex.getPropertyChangeEvent().getPropertyName();
        Object value = ex.getPropertyChangeEvent().getNewValue();
        String[] codes = bindingResult.resolveMessageCodes(ex.getErrorCode(), field);
        Object[] arguments = getArgumentsForBindError(bindingResult.getObjectName(), field);
        String errorMessage = null;
        try {
            errorMessage = GenericsUtils.getFieldDisplayAnnotation(bindingResult.getTarget(), field);
        }
        catch (IllegalArgumentException e1) {
            log.error(e1);
            errorMessage = "";
        }
        catch (SecurityException e1) {
            log.error(e1);
            errorMessage = "";
        }
        catch (IllegalAccessException e1) {
            log.error(e1);
            errorMessage = "";
        }
        catch (InvocationTargetException e1) {
            log.error(e1);
            errorMessage = "";
        }
        catch (NoSuchMethodException e1) {
            log.error(e1);
            errorMessage = "";
        }
        catch (NoSuchFieldException e1) {
            log.error(e1);
            errorMessage = "";
        }
        try {
            errorMessage += getErrorMessage(bindingResult, field);
        }
        catch (IllegalArgumentException e) {
            log.error(e);
            errorMessage += "輸入數據錯誤!";
        }
        catch (SecurityException e) {
            log.error(e);
            errorMessage += "輸入數據錯誤!";
        }
        catch (IllegalAccessException e) {
            log.error(e);
            errorMessage += "輸入數據錯誤!";
        }
        catch (InvocationTargetException e) {
            log.error(e);
            errorMessage += "輸入數據錯誤!";
        }
        catch (NoSuchMethodException e) {
            log.error(e);
            errorMessage += "輸入數據錯誤!";
        }
        bindingResult.addError(new FieldError(bindingResult.getObjectName(), field, value, true, codes, arguments,
                errorMessage));
    }

    @SuppressWarnings("unchecked")
    protected String getErrorMessage(BindingResult bindingResult, String field) throws IllegalArgumentException,
            SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class fieldClass = GenericsUtils.getFieldClass(bindingResult.getTarget(), field);
        if (fieldClass == Integer.class || fieldClass == Long.class) {
            return MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISINTEGER);
        }
        else if (GenericsUtils.isSuperClass(fieldClass, Number.class)) {
            return MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISNUMERIC);
        }
        else if (GenericsUtils.isSuperClass(fieldClass, Date.class)) {
            return MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISDATE);
        }
        else {
            return "輸入數據錯誤!";
        }
    }
}
