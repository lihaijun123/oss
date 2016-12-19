package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.web.AbstractValidator;

/**
 * <li></li>
 *
 * @author chensu 2008-4-21 下午02:57:36
 */
public class UserValidator extends AbstractValidator {
    public UserValidator(OssAdminUser user, Errors e) {
        super(user, e);
    }

    public void validate() {
        super.checkEmputyString("fullname");
        super.checkEmputyString("loginName");
        super.checkIsEmail("email", false);
        // 使用LDAP認證後,密碼是非必填項.
        // super.checkPassword("password");
    }
}
