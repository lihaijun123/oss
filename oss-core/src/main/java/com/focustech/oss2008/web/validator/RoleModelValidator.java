package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.RoleModel;
import com.focustech.oss2008.web.AbstractValidator;

public class RoleModelValidator extends AbstractValidator {
    public RoleModelValidator(RoleModel form, Errors error) {
        super(form, error);
    }

    public void validate() {
        super.checkEmputyObj("roleId");
    }
}
