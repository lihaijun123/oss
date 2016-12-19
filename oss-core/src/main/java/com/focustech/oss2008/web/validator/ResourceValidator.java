package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.web.AbstractValidator;

public class ResourceValidator extends AbstractValidator {
    public ResourceValidator(OssAdminResource resource, Errors e) {
        super(resource, e);
    }

    /**
	 *
	 * */
    public void validate() {
        super.checkEmputyString("resourceName");
        //super.checkEmputyString("resourceInterface");
        super.checkEmputyString("resourceDisplay");
        super.checkEmputyObj("resourceOrder");
        super.checkIsInteger("resourceOrder");
    }
}
