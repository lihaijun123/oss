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

import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.RoleModel;
import com.focustech.oss2008.service.RoleModelService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.RoleModelValidator;

/**
 * <li>角色模塊管理Controller</li>
 *
 * @author jibin 2008-11-18 上午09:57:42
 */
@Controller
@RequestMapping("/roleModel.do")
public class RoleModelController extends AbstractController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleModelService roleModelService;

    /**
     * 進入添加頁面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        RoleModel roleModel = new RoleModel();
        model.addAttribute("roleModel", roleModel);
        List<OssAdminRole> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "role/model/add";
    }

    /**
     * 添加
     *
     * @param roleModel
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.POST)
    public String addRoleModel(RoleModel roleModel, BindingResult result, ModelMap model) {
        new RoleModelValidator(roleModel, result).validate();
        if (result.hasErrors()) {
            List<OssAdminRole> roles = roleService.getAllRoles();
            model.addAttribute("roles", roles);
            return "role/model/add";
        }
        roleModelService.addRoleModel(roleModel);
        return redirect2succ("/uitoolList.ui?funcID=255", "添加成功！", model);
    }

    /**
     * 進入修改頁面
     *
     * @param recordId
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.GET)
    public String modify(Long recordId, ModelMap model) {
        RoleModel roleModel = roleModelService.getRoleModel(recordId);
        model.addAttribute("roleModel", roleModel);
        OssAdminRole role = roleService.getRole(roleModel.getRoleId());
        model.addAttribute("roleName", role.getRoleName());
        return "role/model/modify";
    }

    /**
     * 修改
     *
     * @param roleModel
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.POST)
    public String modifyRoleModel(RoleModel roleModel, BindingResult result, ModelMap model) {
        roleModelService.modifyRoleModel(roleModel);
        return redirect2succ("/uitoolList.ui?funcID=255", "修改成功！", model);
    }

    /**
     * 刪除
     *
     * @param recordId
     * @param model
     * @return
     */
    @RequestMapping(params = "method=delete", method = RequestMethod.GET)
    public String delete(Long recordId, ModelMap model) {
        roleModelService.deleteRoleModel(recordId);
        return redirect2succ("/uitoolList.ui?funcID=255", "刪除成功！", model);
    }

    /**
     * 用于頁面驗證,供EXT調用
     *
     * @throws IOException
     */
    @RequestMapping(params = "method=preValidate", method = RequestMethod.POST)
    public void preValidate(RoleModel roleModel, BindingResult results, HttpServletResponse response)
            throws IOException {
        new RoleModelValidator(roleModel, results).validate();
        response.setContentType("text/html; charset=BIG5");
        response.getWriter().write(convert2JSONString(results.getAllErrors()));
        response.getWriter().flush();
    }
}
