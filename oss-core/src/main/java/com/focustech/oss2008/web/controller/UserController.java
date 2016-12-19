package com.focustech.oss2008.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.MessageUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.service.LoginNameDuplicationException;
import com.focustech.oss2008.exception.service.PasswordInvalidException;
import com.focustech.oss2008.model.DepartmentUserBt;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.DepartmentService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.UserValidator;

/**
 * <li>組織Action</li>
 *
 * @author yangpeng 2008-4-2 下午05:11:17 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@Controller
@RequestMapping("/user.do")
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 进入列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("ossAdminDepartment", new OssAdminDepartment());
        return "/user/list";
    }
    /**
     * 进入列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=query", method = RequestMethod.POST)
    public String query(String departmentId, ModelMap model) {
    	List<OssAdminDepartment> allDepartments = departmentService.getAllDepartments();
    	List<OssAdminDepartment> departmentsByIds = allDepartments;
    	if(StringUtils.isNotEmpty(departmentId) && !"null".equals(departmentId)){
    		departmentsByIds = departmentService.getDepartmentsByIds(departmentId);
    	}
    	model.addAttribute("allDepartment", departmentsByIds);
    	model.addAttribute("ossAdminDepartment", new OssAdminDepartment());
    	return "/user/list";
    }
    /**
     * 進入添加界面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        // 獲取所有角色信息
        OssAdminUser user = new OssAdminUser();
        user.setGender(Constants.SEX_MALE);// 設置性別，默認為男
        user.setActive(Constants.DEPARTMENT_ACTIVE);// 設置業務狀態，默認為啟用
        model.addAttribute("ossAdminUser", user);
        return "/user/add";
    }

    /**
     * 用于頁面驗證,供EXT調用
     *
     * @param user 用戶信息
     * @param results
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "method=preValidate", method = RequestMethod.POST)
    public void preValidate(OssAdminUser user, BindingResult results, HttpServletResponse response) throws IOException {
        new UserValidator(user, results).validate();
        ajaxOutput(response, convert2JSONString(results.getAllErrors()));
    }

    /**
     * 添加用戶操作
     *
     * @param user 用戶信息
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.POST)
    public String add(OssAdminUser user, BindingResult result, ModelMap model) {
        new UserValidator(user, result).validate();
        // 獲取所有角色信息
        if (result.hasErrors()) {
            return "/user/add";
        }
        else {
            try {
                userService.addUser(user);
            }
            catch (LoginNameDuplicationException e) {
                result.rejectValue("loginName", UserValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.USER_HASEXIET_EXCEPTION));
                return "/user/add";
            }
            catch (PasswordInvalidException e) {
                result.rejectValue("password", UserValidator.ERROR_CODE_REQUIRED, e.getMessage());
                return "/user/add";
            }
            return "/user/details";
        }
    }
    /**
     * 删除
     * *
     * @param user
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=delete", method = RequestMethod.GET)
    public String delete(String userId, ModelMap model) {
    	if(StringUtils.isNotEmpty(userId)){
    		userService.delete(userId);
    	}
    	return redirectTo("/user.do?method=list");
    }

    /**
     * 進入修改界面
     *
     * @param userId 用戶編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.GET)
    public String modify(String userId, ModelMap model) {
        OssAdminUser user = userService.getUserById(userId);
        isNull(user, ExceptionConstants.UNUSER_OPERATION_EXCEPTION);
        // 獲取所有角色信息
        model.addAttribute("ossAdminUser", user);
        return "/user/modify";
    }

    /**
     * 修改操作
     *
     * @param user 用戶信息
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.POST)
    public String modify(OssAdminUser user, BindingResult result, ModelMap model) {
        new UserValidator(user, result).validate();
        // 獲取所有角色信息
        if (result.hasErrors()) {
            return "/user/modify";
        }
        else
            try {
                userService.modifyUser(user);
            }
            catch (LoginNameDuplicationException e) {
                result.rejectValue("loginName", UserValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.USER_HASEXIET_EXCEPTION));
                return "/user/modify";
            }
            catch (PasswordInvalidException e) {
                result.rejectValue("password", UserValidator.ERROR_CODE_REQUIRED, e.getMessage());
                return "/user/modify";
            }
        return "/user/details";
    }

    /**
     * 進入查看界面
     *
     * @param userId 用戶編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=details", method = RequestMethod.GET)
    public String details(String userId, ModelMap model) {
        OssAdminUser user = userService.getUserById(userId);
        isNull(user, ExceptionConstants.UNUSER_OPERATION_EXCEPTION);
        // 獲取所有角色信息
        model.addAttribute("ossAdminUser", user);
        return "/user/details";
    }

    /**
     * 進行角色url授權
     *
     * @param role 角色信息
     * @param results
     * @param model
     * @return
     */
    @RequestMapping(params = "method=grantUrl", method = RequestMethod.POST)
    public String grantUrl(String del_id, Long[] sel_id, String userId) {
        userService.modifyUserResourceScope(del_id, sel_id, userId);
        return redirectTo("/uitoolList.ui?funcID=185");
    }

    /**
     * @param user
     * @param results
     * @param response
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(params = "method=depUsers")
    public void getDepartmentUsers(DepartmentUserBt depUser, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map data =
                userService.getUsersByDepartmentIdAndStatus(depUser.getDepartmentId(), depUser.getOrgType(), depUser
                        .getOrgRange(), depUser.getDefOrgId(), depUser.getUserId(), depUser.getUserType(), depUser
                        .getDefUserId(), request.getParameter("resourceId"));
        ajaxOutput(response, convert2JSONString(data));
    }

    @ModelAttribute
    public void setList(ModelMap model){
    	List<OssAdminDepartment> allDepartments = departmentService.getAllDepartments();
    	model.addAttribute("ossAdminRolesList", roleService.getAllRoles());
        model.addAttribute("departmentList", allDepartments);
        model.addAttribute("ossAdminDepartmentList", allDepartments);
        model.addAttribute("allDepartment", allDepartments);
    }
}
