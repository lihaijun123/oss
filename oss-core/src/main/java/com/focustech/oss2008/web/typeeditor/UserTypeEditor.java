package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;

import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.UserService;

public class UserTypeEditor extends PropertyEditorSupport {
    private UserService userService;

    public UserTypeEditor(UserService userService) {
        this.userService = userService;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        OssAdminUser user = userService.getUserById(text);
        super.setValue(user);
    }
}
