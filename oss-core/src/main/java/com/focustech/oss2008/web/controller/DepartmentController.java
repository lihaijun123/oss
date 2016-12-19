package com.focustech.oss2008.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.HttpUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.MessageUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.service.DepartmentNameDuplicationException;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.DepartmentService;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.DepartmentValidator;

/**
 * <li>組織Action</li>
 *
 * @author yangpeng 2008-4-2 下午05:11:17 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@Controller
@RequestMapping("/department.do")
public class DepartmentController extends AbstractController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    /**
     * 進入添加機構界面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=list", method = RequestMethod.GET)
    public String list(String message, ModelMap model) {
    	model.addAttribute("allDepartment", departmentService.getAllDepartments());
    	model.addAttribute("message", message);
        return "/organization/list";
    }

    /**
     * 進入添加機構界面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.GET)
    public String add(String departmentId, ModelMap model) {
        /** 上级部门 */
        OssAdminDepartment department = new OssAdminDepartment();
        /** 当前部门*/
        OssAdminDepartment ossAdminDepartment = new OssAdminDepartment();
        ossAdminDepartment.setActive(Constants.DEPARTMENT_ACTIVE);
        // 指定上級部門的情況,也就是點擊"添加子部門"進入
        if (departmentId != null)
            department = departmentService.getDepartment(departmentId);
        // 如果指定的上級存在,則設置一些屬性
        if (department != null) {
            ossAdminDepartment.setDepartmentParentId(department.getDepartmentId());
            ossAdminDepartment.setDepartmentType(department.getDepartmentType());// 設置機構類型，與上級部門一致
        }
        model.addAttribute("ossAdminDepartment", ossAdminDepartment);
        return "/organization/add";
    }

    /**
     * 添加機構操作
     *
     * @param organization 機構信息
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.POST)
    public String add(OssAdminDepartment department, BindingResult result, ModelMap model) {
        new DepartmentValidator(department, result).validate();
        if (result.hasErrors()) {
            return "organization/add";
        }
        else {
            try {
                departmentService.addDepartment(department);
                // 獲取上級部門，用于頁面顯示其名稱
                model.addAttribute("parentDepartment", departmentService.getDepartment(department
                        .getDepartmentParentId()));
            }
            catch (DepartmentNameDuplicationException e) {
                result.rejectValue("departmentName", DepartmentValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.ORGAN_HASEXIET_EXCEPTION));
                return "/organization/add";
            }
        }
        return "/organization/details";
    }
    /**
     *
     * *
     * @param department
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=delete", method = RequestMethod.GET)
    public String delete(String departmentId, ModelMap model) {
    	if(StringUtils.isNotEmpty(departmentId)){
    		List<OssAdminUser> users = userService.getUsersByDepartment(departmentId);
    		if(ListUtils.isNotEmpty(users)){
    			OssAdminDepartment department = departmentService.getDepartment(departmentId);
    			String dName = "";
    			if(department != null){
    				dName = department.getDepartmentName();
    			}
    			return redirectTo("/department.do?method=list&message=" + HttpUtil.encodeUrl(dName + " 部门下有用户请先删除下面用户" ));
    		} else {
    			departmentService.delete(departmentId);
    		}
    	}
    	return redirectTo("/department.do?method=list");
    }

    /**
     * 進入修改機構界面
     *
     * @param organizationId 機構編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.GET)
    public String modify(String departmentId, ModelMap model) {
        OssAdminDepartment department = departmentService.getDepartment(departmentId);
        isNull(department, ExceptionConstants.UNORGAN_OPERATION_EXCEPTION);
        model.addAttribute("ossAdminDepartment", department);
        return "/organization/modify";
    }

    /**
     * 修改機構操作
     *
     * @param organization 機構信息
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.POST)
    public String modify(OssAdminDepartment department, BindingResult result, ModelMap model) {
        new DepartmentValidator(department, result).validate();
        if (result.hasErrors()) {
            return "/organization/modify";
        }
        else
            try {
                departmentService.modifyDepartment(department);
                // 獲取上級部門，用于頁面顯示其名稱
                model.addAttribute("parentDepartment", departmentService.getDepartment(department
                        .getDepartmentParentId()));
            }
            catch (DepartmentNameDuplicationException e) {
                result.rejectValue("departmentName", DepartmentValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.ORGAN_HASEXIET_EXCEPTION));
                return "/organization/modify";
            }
        return "/organization/details";
    }

    /**
     * 進入查看機構界面
     *
     * @param organizationId 機構編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=details", method = RequestMethod.GET)
    public String details(String departmentId, ModelMap model) {
        OssAdminDepartment department = departmentService.getDepartment(departmentId);
        isNull(department, ExceptionConstants.UNORGAN_OPERATION_EXCEPTION);
        model.addAttribute("ossAdminDepartment", department);
        // 獲取上級部門，用于頁面顯示其名稱
        model.addAttribute("parentDepartment", departmentService.getDepartment(department.getDepartmentParentId()));
        return "/organization/details";
    }

    /**
     * 用于頁面驗證,供EXT調用
     *
     * @throws IOException
     */
    @RequestMapping(params = "method=preValidate", method = RequestMethod.POST)
    public void preValidate(OssAdminDepartment departments, BindingResult results, HttpServletResponse response)
            throws IOException {
        new DepartmentValidator(departments, results).validate();
        ajaxOutput(response, convert2JSONString(results.getAllErrors()));
    }
}
