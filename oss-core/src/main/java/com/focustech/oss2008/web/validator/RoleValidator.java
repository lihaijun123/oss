package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.web.AbstractValidator;

public class RoleValidator extends AbstractValidator {

    public RoleValidator(OssAdminRole role, Errors error) {
        super(role, error);
    }

    public void validate() {
        super.checkEmputyString("roleName");
    }
}
