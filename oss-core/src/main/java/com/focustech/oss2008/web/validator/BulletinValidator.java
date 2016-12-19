package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.OssAdminBulletin;
import com.focustech.oss2008.web.AbstractValidator;

public class BulletinValidator extends AbstractValidator {
    public BulletinValidator(OssAdminBulletin bulletin, Errors error) {
        super(bulletin, error);
    }

    public void validate() {
        super.checkEmputyObj("bulletinModel");
        super.checkEmputyString("bulletinTitle");
        super.checkEmputyObj("active");
    }
}
