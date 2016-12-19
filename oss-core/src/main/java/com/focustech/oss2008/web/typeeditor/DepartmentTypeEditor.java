package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;

import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.service.DepartmentService;

/**
 * <li>組織屬性編輯器</li>
 *
 * @author yangpeng 2008-4-16 下午04:39:59
 */
public class DepartmentTypeEditor extends PropertyEditorSupport {
    private DepartmentService departmentService;

    public DepartmentTypeEditor(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        OssAdminDepartment department = departmentService.getDepartment(text);
        super.setValue(department);
    }
}
