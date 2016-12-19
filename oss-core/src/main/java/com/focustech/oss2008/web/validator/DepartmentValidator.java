package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.web.AbstractValidator;

public class DepartmentValidator extends AbstractValidator {
    public DepartmentValidator(OssAdminDepartment department, Errors error) {
        super(department, error);
    }

    public void validate() {
        super.checkEmputyString("departmentName");
        //super.checkEmputyString("departmentType");
        //super.checkEmputyString("active");
    }
}
