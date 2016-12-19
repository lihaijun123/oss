package com.focustech.oss2008.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.HttpUtil;
import com.focustech.common.utils.MessageUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.service.OssRollbackCheckedException;
import com.focustech.oss2008.exception.service.RoleNameDuplicationException;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.RoleValidator;

@Controller
@RequestMapping("/role.do")
public class RoleController extends AbstractController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    /**
     * 进入列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=list", method = RequestMethod.GET)
    public String list(String message, ModelMap model) {
    	model.addAttribute("message", message);
        return "/role/list";
    }
    /**
     * 進入添加角色界面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.GET)
    public String add(Model model) {
        OssAdminRole role = new OssAdminRole();
        model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
        model.addAttribute("ossAdminRole", role);
        return "/role/add";
    }

    /**
     * 添加角色操作
     *
     * @param role 角色信息
     * @param results
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.POST)
    public String add(Model model, OssAdminRole role, BindingResult results) {
        model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
        new RoleValidator(role, results).validate();
        if (results.hasErrors()) {
            return "role/add";
        }
        else {
            try {
                roleService.addRole(role);
            }
            catch (RoleNameDuplicationException e) {
                results.rejectValue("roleName", RoleValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
                return "role/add";
            }
        }
        return "/role/details";
    }

    /**
     * 進入復制角色界面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=copy", method = RequestMethod.GET)
    public String copy(Long roleId, String roleName, Model model) {
        OssAdminRole role = new OssAdminRole();
        role.setActive(Constants.ACTIVE_TYPE_ALLOW);
        model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
        model.addAttribute("ossAdminRole", role);
        model.addAttribute("option", roleName);
        model.addAttribute("roleId", roleId);
        model.addAttribute("resource", "1");
        model.addAttribute("queue", "1");
        return "/role/copy_role";
    }

    /**
     * 復制角色操作
     *
     * @param role 角色信息
     * @param results
     * @return
     * @throws OssRollbackCheckedException
     */
    @RequestMapping(params = "method=copy", method = RequestMethod.POST)
    public String copy(Long roleId, String resource, String queue, String option, Model model, OssAdminRole role,
            BindingResult results) throws OssRollbackCheckedException {
        model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
        new RoleValidator(role, results).validate();
        if (results.hasErrors()) {
            model.addAttribute("roleId", roleId);
            model.addAttribute("option", option);
            model.addAttribute("resource", resource);
            model.addAttribute("queue", queue);
            return "role/copy_role";
        }
        else {
            try {
                roleService.copyRole(roleId, "1".equals(resource), "1".equals(queue), role);
            }
            catch (RoleNameDuplicationException e) {
                results.rejectValue("roleName", RoleValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
                model.addAttribute("roleId", roleId);
                model.addAttribute("option", option);
                model.addAttribute("resource", resource);
                model.addAttribute("queue", queue);
                return "role/copy_role";
            }
        }
        return "/role/details";
    }

    /**
     * 進入修改角色界面
     *
     * @param roleId 角色編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.GET)
    public String modify(String roleId, Model model) {
        OssAdminRole role = roleService.getRole(Long.valueOf(roleId));
        if (role != null) {
            model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                    OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
            model.addAttribute("ossAdminRole", role);
            return "/role/modify";
        }
        else {
            return jump2DefaultErrorPage(MessageUtils.getExceptionValue(ExceptionConstants.UNROLE_OPERATION_EXCEPTION));
        }
    }

    /**
     * 進入查看角色界面
     *
     * @param roleId 角色編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=details", method = RequestMethod.GET)
    public String details(String roleId, Model model) {
        OssAdminRole role = roleService.getRole(Long.valueOf(roleId));
        if (role != null) {
            model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                    OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
            model.addAttribute("ossAdminRole", role);
            return "/role/details";
        }
        else {
            return jump2DefaultErrorPage(MessageUtils.getExceptionValue(ExceptionConstants.UNROLE_OPERATION_EXCEPTION));
        }
    }

    /**
     * 進行修改操作
     *
     * @param role 角色信息
     * @param results
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.POST)
    public String modify(Model model, OssAdminRole role, BindingResult results) {
        new RoleValidator(role, results).validate();
        if (results.hasErrors()) {
            return "role/modify";
        }
        else
            try {
                roleService.modifyRole(role, "");
            }
            catch (RoleNameDuplicationException e) {
                results.rejectValue("roleName", RoleValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
                return "role/modify";
            }
        return "/role/details";
    }

    /**
     * 刪除一個角色信息
     *
     * @param role 角色信息
     * @param results
     * @return
     */
    @RequestMapping(params = "method=delete", method = RequestMethod.GET)
    public String delete(String roleId, Model model) {
    	if(StringUtils.isNotEmpty(roleId)){
    		boolean hasUser = userService.hasUser(roleId);
    		if(!hasUser){
    			OssAdminRole role = roleService.getRole(Long.valueOf(roleId));
    			if (role != null){
    				roleService.deleteRole(role);
    			}
    		} else {
    			return redirectTo("/role.do?method=list&message=" + HttpUtil.encodeUrl("此角色下存在用户，请先删除用户"));
    		}
    	}
    	return redirectTo("/role.do?method=list");
    }

    /**
     * 進入角色列表界面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=list")
    public String visitAll(Model model) {
        model.addAttribute("OssAdminRoleList", roleService.getAllRoles());
        return "role/list";
    }

    /**
     * 用于頁面驗證,供EXT調用
     *
     * @throws IOException
     */
    @RequestMapping(params = "method=preValidate", method = RequestMethod.POST)
    public void preValidate(OssAdminRole roles, BindingResult results, HttpServletResponse response) throws IOException {
        new RoleValidator(roles, results).validate();
        response.setContentType("text/html; charset=BIG5");
        response.getWriter().write(convert2JSONString(results.getAllErrors()));
        response.getWriter().flush();
    }

    // **********************角色授權操作***********************************************//
    /**
     * 進入角色菜單授權操作界面
     *
     * @param roleId 角色編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=grantMenu", method = RequestMethod.GET)
    public String grantMenu(String roleId, ModelMap model) {
        return processGrantGetMethod(ResourceService.RESOURCE_TYPE_MENU, ResourceService.RESOURCE_TYPE_MENU_NAME,
                roleId, model);
    }

    /**
     * 進行角色菜單授權
     *
     * @param role 角色信息
     * @param results
     * @param model
     * @return
     */
    @RequestMapping(params = "method=grantMenu", method = RequestMethod.POST)
    public String grantMenu(OssAdminRole role, BindingResult results, Model model) {
        return processGrantPostMethod(ResourceService.RESOURCE_TYPE_MENU, ResourceService.RESOURCE_TYPE_MENU_NAME, role, results, model);
    }
    /*@RequestMapping(params = "method=grantMenu", method = RequestMethod.POST)
    public String grantMenu(String roleName, String resourceSet, Model model) {
    	OssAdminRole adminRole = new OssAdminRole();
    	adminRole.setRoleName(roleName);
    	Set<OssAdminResource> set = new HashSet<OssAdminResource>();
        String[] resources = null;
        int i;
        if (!StringUtils.isEmpty(resourceSet)) {
            resources = resourceSet.split(",");
            for (i = 0; i < resources.length; i++) {
                set.add(resourceService.getResource(Long.valueOf(resources[i])));
            }
            adminRole.setResources(set);
        }
        return processGrantPostMethod(ResourceService.RESOURCE_TYPE_MENU_NAME, role, results, model);
    }*/
    /**
     * 進入角色url授權操作界面
     *
     * @param roleId 角色編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=grantUrl", method = RequestMethod.GET)
    public String grantUrl(String roleId, ModelMap model) {
        return processGrantGetMethod(ResourceService.RESOURCE_TYPE_URL, ResourceService.RESOURCE_TYPE_URL_NAME, roleId,
                model);
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
    public String grantUrl(String del_id, String[] dataScope, Long[] sel_id, Long roleId) {
        roleService.modifyRoleResourceScope(roleId, del_id, sel_id, dataScope);
        return redirectTo("/uitoolList.ui?funcID=226");
    }

    @RequestMapping(params = "method=grantWorkspace", method = RequestMethod.GET)
    public String grantWorkspace(String roleId, ModelMap model) {
        return processGrantGetMethod(ResourceService.RESOURCE_TYPE_WORKSPACE, ResourceService.RESOURCE_TYPE_WORKSPACE_NAME, roleId,
                model);
    }

    @RequestMapping(params = "method=grantWorkspace", method = RequestMethod.POST)
    public String grantWorkspace(OssAdminRole role, BindingResult results, Model model) {
    	return processGrantPostMethod(ResourceService.RESOURCE_TYPE_WORKSPACE, ResourceService.RESOURCE_TYPE_WORKSPACE_NAME, role, results, model);
    }


    /**
     * 進入角色方法授權操作界面
     *
     * @param roleId 角色編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=grantMethod", method = RequestMethod.GET)
    public String grantMethod(String roleId, ModelMap model) {
        return processGrantGetMethod(ResourceService.RESOURCE_TYPE_METHOD, ResourceService.RESOURCE_TYPE_METHOD_NAME,
                roleId, model);
    }

    /**
     * 進行角色方法授權
     *
     * @param role 角色信息
     * @param results
     * @param model
     * @return
     */
    @RequestMapping(params = "method=grantMethod", method = RequestMethod.POST)
    public String grantMethod(OssAdminRole role, BindingResult results, Model model) {
        return processGrantPostMethod(ResourceService.RESOURCE_TYPE_METHOD, ResourceService.RESOURCE_TYPE_METHOD_NAME, role, results, model);
    }

    /**
     * 處理授權的GET方法
     */
    private String processGrantGetMethod(String resource, String resourceName, String roleId, ModelMap model) {
        model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
        OssAdminRole role = roleService.getRole(Long.valueOf(roleId));
        List<OssAdminResource> menuByRole = roleService.getResourceByRole(Long.valueOf(roleId), resource);
		model.addAttribute("resourceSelected", menuByRole);
        if (role != null) {
            model.addAttribute("ossAdminRole", role);
            model.addAttribute("ossAdminResourseList", resourceService.getResourceByType(resource));
            return "role/grant/" + resourceName;
        }
        else {
            return jump2DefaultErrorPage(MessageUtils.getExceptionValue(ExceptionConstants.UNROLE_OPERATION_EXCEPTION));
        }
    }

    /**
     * 處理授權的POST方法
     */
    private String processGrantPostMethod(String resource, String resourceName, OssAdminRole role, BindingResult results, Model model) {
        model.addAttribute("crmBaseParameterActiveList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_ACTIVE, Constants.SITE_TYPE_MIC_CN));
        model.addAttribute("OssAdminRoleList", roleService.getAllRoles());
        new RoleValidator(role, results).validate();
        if (results.hasErrors()) {
            return "role/grant/" + resourceName;
        }
        else
            try {
                roleService.modifyRole(role, resource);
            }
            catch (RoleNameDuplicationException e) {
                results.rejectValue("roleName", "required", MessageUtils
                        .getExceptionValue(ExceptionConstants.ROLE_HASEXIET_EXCEPTION));
                return "role/grant/" + resourceName;
            }
        return "/role/list";
    }

    @ModelAttribute
    public void setList(Model model){
    	model.addAttribute("OssAdminRoleList", roleService.getAllRoles());
    }
    // **************************************角色授權操作***********************************************//
}
