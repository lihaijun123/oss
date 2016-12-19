package com.focustech.oss2008.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.service.DepartmentService;
import com.focustech.oss2008.service.ShowOrganTree;
import com.focustech.oss2008.web.AbstractController;

/**
 * <li>機構樹</li>
 *
 * @author jibin 2008-11-20 上午10:18:02
 */
@Controller
@RequestMapping("/showOrganTree.do")
public class ShowOrganTreeController extends AbstractController {
    @Autowired
    private ShowOrganTree showOrganTree;
    @Autowired
    private DepartmentService DepartmentService;

    /**
     * 顯示機構及其下屬機構樹
     *
     * @param departmentId
     * @param model
     * @return
     */
    @RequestMapping(params = "method=showOrgan", method = RequestMethod.GET)
    public String show(String departmentId, ModelMap model) {
        OssAdminDepartment department = DepartmentService.getDepartment(departmentId);
        model.addAttribute("dName", department.getDepartmentName());
        String url = "'/showOrganTree.do?method=showOrganTree&departmentId=" + departmentId + "'";
        model.addAttribute("url", url);
        return "showOrgan";
    }

    /**
     * 顯示機構及其下屬機構樹
     *
     * @param departmentId
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "method=showOrganTree", method = RequestMethod.POST)
    public void showOrgan(String departmentId, HttpServletResponse response) throws IOException {
        String menu = showOrganTree.getOrganByDeptId(departmentId);
        response.setContentType("text/html; charset=BIG5");
        response.getWriter().write(menu);
        response.getWriter().flush();
    }
}
