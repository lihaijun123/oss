package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ValidateConstants;
import com.focustech.oss2008.web.AbstractValidator;

public class CrmRemindValidator extends AbstractValidator {
    public CrmRemindValidator(Object form, Errors error) {
        super(form, error);
    }

    public void validate() {
        super.checkEmputyString("remindTitle", MessageUtils.getValidatorValue(ValidateConstants.REMIND_TITLE_NOT_NULL));
        super.checkStringLengthMax("remindTitle", 500, MessageUtils
                .getValidatorValue(ValidateConstants.REMIND_TITLE_MAXLENGTH));
        super.checkEmputyObj("remindTime", MessageUtils.getValidatorValue(ValidateConstants.REMIND_TIME_NOT_NULL));
        super.checkStringLengthMax("remindContent", 4000, MessageUtils
                .getValidatorValue(ValidateConstants.REMIND_CONTENT_MAXLENGTH));
    }
}
