package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.OssAdminProxyUser;
import com.focustech.oss2008.web.AbstractValidator;

public class ProxyusersValidator extends AbstractValidator {
    public ProxyusersValidator(OssAdminProxyUser ossAdminProxyusers, Errors error) {
        super(ossAdminProxyusers, error);
    }

    public void validate() {
        super.checkEmputyString("userId");
        super.checkEmputyString("proxyId");
        super.checkEmputyObj("startTime");
        super.checkEmputyObj("endTime");
    }
}
