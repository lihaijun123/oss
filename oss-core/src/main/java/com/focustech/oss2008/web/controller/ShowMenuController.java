package com.focustech.oss2008.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.exception.OssUncheckedException;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.ShowMenuTree;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.web.AbstractController;

@Controller
@RequestMapping("/showMenu.do")
public class ShowMenuController extends AbstractController {
    @Autowired
    private ShowMenuTree showMenuTree;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping(params = "method=getMenuByRole", method = RequestMethod.GET)
    public String getMenuByRole(Long roleId, ModelMap model) {
        model.addAttribute("msg", roleService.getRole(roleId).getRoleName());
        model.addAttribute("url", "'/showMenu.do?method=showRoleMenu&roleId=" + roleId + "'");
        return "showMenu";
    }

    @RequestMapping(params = "method=getMenuByUser", method = RequestMethod.GET)
    public String getMenuByUser(String userId, ModelMap model) {
        OssAdminUser user = userService.getUserById(userId);
        if (user == null || Constants.ACTIVE_TYPE_FORBID.equals(user.getActive())) {
            throw new OssUncheckedException("�ӥΤ�w���s�b�Τw�T�ΡI");
        }
        model.addAttribute("msg", user.getFullname());
        model.addAttribute("url", "'/showMenu.do?method=showUserMenu&userId=" + userId + "'");
        return "showMenu";
    }

    @RequestMapping(params = "method=showRoleMenu", method = RequestMethod.POST)
    public void showRoleMenu(Long roleId, HttpServletResponse response) throws IOException {
        String menu = showMenuTree.getMenuByRole(roleId);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(menu);
        response.getWriter().flush();
    }

    @RequestMapping(params = "method=showUserMenu", method = RequestMethod.POST)
    public void showUserMenu(String userId, HttpServletResponse response) throws IOException {
        String menu = showMenuTree.getMenuByUser(userService.getUserById(userId));
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(menu);
        response.getWriter().flush();
    }

}
