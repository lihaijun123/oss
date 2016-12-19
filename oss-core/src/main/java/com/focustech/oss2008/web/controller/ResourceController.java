package com.focustech.oss2008.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.MessageUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.service.ResourceNameDuplicationException;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.UitoolFuncDisplayInfo;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.UitoolFuncDisplayInfoService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.ResourceValidator;

@Controller
@RequestMapping("/resource.do")
public class ResourceController extends AbstractController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
	private UitoolFuncDisplayInfoService<UitoolFuncDisplayInfo> funcDisplayInfoService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UitoolFuncDisplayInfoService<UitoolFuncDisplayInfo> uiDisplayInfoService;

    private void initModelView(ModelMap model) {
        model.addAttribute("crmBaseParameterRESOURCEType", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_RESOURCE_TYPE, Constants.SITE_TYPE_MIC_CN));
        model.addAttribute("crmBaseParameterRESOURCEList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_RESOURCE, Constants.SITE_TYPE_MIC_CN));
        model.addAttribute("crmBaseParameterDISPLAYList", parametersService.listParameters(
                OssAdminParameterService.PARAM_TYPE_DISPLAY, Constants.SITE_TYPE_MIC_CN));
    }

    private void commonAdd(ModelMap model) {
        initModelView(model);
        OssAdminResource resource = new OssAdminResource();
        // resource.setResourceDisplay(Constants.RESOURCE_DISPLAY);
        model.addAttribute(resource);
    }

    @RequestMapping(params = "method=menuAdd", method = RequestMethod.GET)
    public String addMenu(ModelMap model) {
        commonAdd(model);
        ((OssAdminResource) model.get("ossAdminResource")).setResourceType(ResourceService.RESOURCE_TYPE_MENU);
        model.addAttribute("menuResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_MENU));
        model.addAttribute("OssAdminResourceList", resourceService.getAllResources());
        return "/resource/menu/add";
    }

    @RequestMapping(params = "method=methodAdd", method = RequestMethod.GET)
    public String addMethod(ModelMap model) {
        commonAdd(model);
        ((OssAdminResource) model.get("ossAdminResource")).setResourceType(ResourceService.RESOURCE_TYPE_METHOD);
        model.addAttribute("methodResourcesList", resourceService
                .getResourceByType(ResourceService.RESOURCE_TYPE_METHOD));
        return "/resource/method/add";
    }

    @RequestMapping(params = "method=urlAdd", method = RequestMethod.GET)
    public String addUrl(ModelMap model) {
        commonAdd(model);
        ((OssAdminResource) model.get("ossAdminResource")).setResourceType(ResourceService.RESOURCE_TYPE_URL);
        model.addAttribute("urlResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_URL));
        return "/resource/url/add";
    }

    @RequestMapping(params = "method=workspaceAdd", method = RequestMethod.GET)
    public String addWorkspace(ModelMap model) {
    	commonAdd(model);
    	((OssAdminResource) model.get("ossAdminResource")).setResourceType(ResourceService.RESOURCE_TYPE_WORKSPACE);
    	model.addAttribute("workspaceResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_WORKSPACE));
    	return "/resource/workspace/add";
    }
    @RequestMapping(params = "method=workspaceAdd", method = RequestMethod.POST)
    public String addWorkspace(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processAddPost(ResourceService.RESOURCE_TYPE_WORKSPACE_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=workspaceModify", method = RequestMethod.GET)
    public String modifyWorkspace(String resourceId, ModelMap model) {
    	return processModifyGet(ResourceService.RESOURCE_TYPE_WORKSPACE_NAME, resourceId, model);
    }

    @RequestMapping(params = "method=workspaceModify", method = RequestMethod.POST)
    public String modifyWorkspace(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processModifyPost(ResourceService.RESOURCE_TYPE_WORKSPACE_NAME, resource, result, model);
    }

    private String processAddPost(String url, OssAdminResource resource, BindingResult results, ModelMap model) {
        initModelView(model);
        model.addAttribute("menuResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_MENU));
        model.addAttribute("urlResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_URL));
        model.addAttribute("methodResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_METHOD));
        model.addAttribute("workspaceResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_WORKSPACE));
        model.addAttribute("OssAdminResourceList", resourceService.getAllResources());
        new ResourceValidator(resource, results).validate();
        if (results.hasErrors()) {
            model.addAttribute("message", "添加" + url + "資源失敗!");
            return "resource/" + url + "/add";
        }
        else
            try {
                resourceService.addResource(resource);
                if (StringUtils.isEmpty(url)) {
                    return redirect2succ("/resource.do?method=methodModify&resourceId=" + resource.getResourceId(),
                            "添加" + url + "資源成功!", model);
                }
                else {
                    return redirect2succ(
                            "/resource.do?method=" + url + "Modify&resourceId=" + resource.getResourceId(), "添加" + url
                                    + "資源成功!", model);
                }

            }
            catch (ResourceNameDuplicationException e) {
                results.rejectValue("resourceName", ResourceValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.RESOURCE_HASEXIET_EXCEPTION));
                return "resource/" + url + "/add";
            }
    }

    /**
     * @param url
     * @param method
     * @param resourceId
     * @param model
     * @return
     */
    private String processModifyPost(String url, OssAdminResource resource, BindingResult results, ModelMap model) {
        initModelView(model);
        model.addAttribute("menuResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_MENU));
        model.addAttribute("urlResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_URL));
        model.addAttribute("methodResourcesList", resourceService
                .getResourceByType(ResourceService.RESOURCE_TYPE_METHOD));
        model.addAttribute("OssAdminResourceList", resourceService.getAllResources());
        model.addAttribute(resource);
        new ResourceValidator(resource, results).validate();
        if (results.hasErrors()) {
            model.addAttribute("message", "修改" + url + "資源失敗!");
            return "resource/" + url + "/modify";
        }
        else {
            try {
                OssAdminResource ossAdminResource = resourceService.getResource(resource.getResourceId());
                isNull(ossAdminResource, ExceptionConstants.RESOURCE_NOT_EXIST_EXCEPTION);
                resourceService.modifyResource(resource);
                model.addAttribute("message", "修改" + url + "資源成功!");
                if (url.equals("menu")) {
                    return modifyMenu(resource.getResourceId().toString(), model);
                }
                else if (url.equals("url")) {
                    return modifyUrl(resource.getResourceId().toString(), model);
                }
                else if(url.equals("workspace")){
                	return modifyWorkspace(resource.getResourceId().toString(), model);
                }
                else {
                    return modifyMethod(resource.getResourceId().toString(), model);
                }
            }
            catch (ResourceNameDuplicationException e) {
                results.rejectValue("resourceName", ResourceValidator.ERROR_CODE_DUPLICATED, MessageUtils
                        .getExceptionValue(ExceptionConstants.RESOURCE_HASEXIET_EXCEPTION));
                return "resource/" + url + "/modify";
            }
        }
    }

    private String processModifyGet(String url, String resourceId, ModelMap model) {
        OssAdminResource resource = resourceService.getResource(Long.valueOf(resourceId));
        if (resource != null) {
            initModelView(model);
            model.addAttribute("menuResourcesList", resourceService
                    .getResourceByType(ResourceService.RESOURCE_TYPE_MENU));
            model
                    .addAttribute("urlResourcesList", resourceService
                            .getResourceByType(ResourceService.RESOURCE_TYPE_URL));
            model.addAttribute("methodResourcesList", resourceService
                    .getResourceByType(ResourceService.RESOURCE_TYPE_METHOD));
            model.addAttribute("OssAdminResourceList", resourceService.getAllResources());
            model.addAttribute(resource);
            UitoolFuncDisplayInfo fucDisplayInf = uiDisplayInfoService.selectByfuncId(TCUtil.lv(resourceId));
            model.addAttribute("isExistFunc", fucDisplayInf != null);
            return "/resource/" + url + "/modify";
        }
        else {
            return jump2DefaultErrorPage(MessageUtils
                    .getExceptionValue(ExceptionConstants.RESOURCE_NOT_EXIST_EXCEPTION));
        }
    }

    @RequestMapping(params = "method=preValidate", method = RequestMethod.POST)
    public void preValidate(OssAdminResource resource, BindingResult results, HttpServletResponse response)
            throws IOException {
        new ResourceValidator(resource, results).validate();
        response.setContentType("text/html; charset=BIG5");
        response.getWriter().write(convert2JSONString(results.getAllErrors()));
        response.getWriter().flush();
    }

    @RequestMapping(params = "method=menuAdd", method = RequestMethod.POST)
    public String addMenu(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processAddPost(ResourceService.RESOURCE_TYPE_MENU_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=methodAdd", method = RequestMethod.POST)
    public String addMethod(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processAddPost(ResourceService.RESOURCE_TYPE_METHOD_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=urlAdd", method = RequestMethod.POST)
    public String addUrl(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processAddPost(ResourceService.RESOURCE_TYPE_URL_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=menuModify", method = RequestMethod.GET)
    public String modifyMenu(String resourceId, ModelMap model) {
        return processModifyGet(ResourceService.RESOURCE_TYPE_MENU_NAME, resourceId, model);
    }

    @RequestMapping(params = "method=urlModify", method = RequestMethod.GET)
    public String modifyUrl(String resourceId, ModelMap model) {
        return processModifyGet(ResourceService.RESOURCE_TYPE_URL_NAME, resourceId, model);
    }

    @RequestMapping(params = "method=methodModify", method = RequestMethod.GET)
    public String modifyMethod(String resourceId, ModelMap model) {
        return processModifyGet(ResourceService.RESOURCE_TYPE_METHOD_NAME, resourceId, model);
    }

    @RequestMapping(params = "method=menuModify", method = RequestMethod.POST)
    public String modifyMenu(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processModifyPost(ResourceService.RESOURCE_TYPE_MENU_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=urlModify", method = RequestMethod.POST)
    public String modifyUrl(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processModifyPost(ResourceService.RESOURCE_TYPE_URL_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=methodModify", method = RequestMethod.POST)
    public String modifyMethod(OssAdminResource resource, BindingResult result, ModelMap model) {
        return processModifyPost(ResourceService.RESOURCE_TYPE_METHOD_NAME, resource, result, model);
    }

    @RequestMapping(params = "method=list")
    public String visitResources(ModelMap model) {
        initModelView(model);
        model.addAttribute("menuResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_MENU));
        model.addAttribute("urlResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_URL));
        model.addAttribute("methodResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_METHOD));
        model.addAttribute("workspaceResourcesList", resourceService.getResourceByType(ResourceService.RESOURCE_TYPE_WORKSPACE));
        model.addAttribute("OssAdminResourceList", resourceService.getAllResources());
        return "resource/list";
    }

    @RequestMapping(params = "method=details", method = RequestMethod.GET)
    public String detailResource(String url, String resourceId, ModelMap model) {
        OssAdminResource resource = resourceService.getResource(Long.valueOf(resourceId));
        if (resource != null) {
            initModelView(model);
            model.addAttribute("menuResourcesList", resourceService
                    .getResourceByType(ResourceService.RESOURCE_TYPE_MENU));
            model.addAttribute(resource);
            model.addAttribute("url", url);
            return "/resource/details";
        }
        else {
            return jump2DefaultErrorPage(MessageUtils
                    .getExceptionValue(ExceptionConstants.RESOURCE_NOT_EXIST_EXCEPTION));
        }
    }

    @RequestMapping(params = "method=delete", method = RequestMethod.GET)
    public String delete(String funcID, String resourceId, Model model) {
        OssAdminResource ossAdminResource = resourceService.getResource(Long.valueOf(resourceId));
        isNull(ossAdminResource, ExceptionConstants.RESOURCE_NOT_EXIST_EXCEPTION);
        resourceService.deleteResource(ossAdminResource);
        return redirectTo("/resource.do?method=list");
    }
    /**
     * 导出数据库脚本
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=exportSql", method = RequestMethod.GET)
    public String exportSql(String resourceId, ModelMap model) {
    	String content = "";
    	if(com.focustech.common.utils.StringUtils.isNotEmpty(resourceId)){
    		List<OssAdminRole> allRoles = roleService.getAllRoles();
    		OssAdminRole adminRole = null;
    		for (OssAdminRole ossAdminRole : allRoles) {
				if("admin".equals(ossAdminRole.getRoleName())){
					adminRole = ossAdminRole;
					break;
				}
			}
    		if(adminRole == null){
    			content += "系统至少要有一个名称为admin的管理员。";
    		} else {
    			Long roleId = adminRole.getRoleId();
    			content += resourceService.exportDeleteListSql(TCUtil.lv(resourceId), roleId);
    			content += resourceService.exportInsertListSql(TCUtil.lv(resourceId), roleId);
    			OssAdminResource resource = resourceService.getResource(TCUtil.lv(resourceId));
    			String flag = resource.getFlag();
    			if(com.focustech.common.utils.StringUtils.isNotEmpty(flag) && flag.contains("funcID=")){
    				String funcIdStr = flag.substring(flag.indexOf("=") + 1, flag.length());
    				long funcId = TCUtil.lv(funcIdStr);
    				if(funcId > 0){
    					String deleteSql= funcDisplayInfoService.exportDeleteListSql(funcId);
    					String insertSql = funcDisplayInfoService.exportInsertListSql(funcId);
    					content += deleteSql;
    					content += insertSql;
    				}
    			} else {
    				content += "请选择叶子节点。";
    			}
    		}

    	}
    	model.addAttribute("exportSql", content);
    	return "/resource/exportSql";
    }

}
