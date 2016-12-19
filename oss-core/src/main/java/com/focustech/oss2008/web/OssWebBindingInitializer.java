package com.focustech.oss2008.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.focustech.oss2008.model.OssAdminBulletin;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.BulletinService;
import com.focustech.oss2008.service.DepartmentService;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.web.typeeditor.BulletinEditor;
import com.focustech.oss2008.web.typeeditor.CustomNumberEditor;
import com.focustech.oss2008.web.typeeditor.DateTypeEditor;
import com.focustech.oss2008.web.typeeditor.DepartmentTypeEditor;
import com.focustech.oss2008.web.typeeditor.ResourceTypeEditor;
import com.focustech.oss2008.web.typeeditor.RoleTypeEditor;
import com.focustech.oss2008.web.typeeditor.SingleRoleTypeEditor;
import com.focustech.oss2008.web.typeeditor.UserTypeEditor;

/**
 * 自定义表单属性编辑器（全局）
 * *
 * @author lihaijun
 *
 */
public class OssWebBindingInitializer implements WebBindingInitializer {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private BulletinService bulletinService;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.bind.support.WebBindingInitializer#initBinder(org.springframework.web.bind.WebDataBinder,
     * org.springframework.web.context.request.WebRequest)
     */
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new DateTypeEditor(true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(OssAdminDepartment.class, new DepartmentTypeEditor(departmentService));
        binder.registerCustomEditor(OssAdminRole.class, "role", new SingleRoleTypeEditor(roleService));
        binder.registerCustomEditor(Set.class, "roles", new RoleTypeEditor(roleService));
        binder.registerCustomEditor(Set.class, "resources", new ResourceTypeEditor(resourceService));
        binder.registerCustomEditor(OssAdminUser.class, new UserTypeEditor(userService));
        binder.registerCustomEditor(OssAdminBulletin.class, new BulletinEditor(bulletinService));
    }
}
