package com.focustech.oss2008.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.model.OssAdminProxyUser;
import com.focustech.oss2008.service.ProxyUsersService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.ProxyusersValidator;

@Controller
@RequestMapping("/proxy.do")
public class ProxyUsersController extends AbstractController {
    @Autowired
    private ProxyUsersService proxyusersService;

    /**
     * 進入添加頁面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        OssAdminProxyUser proxyUser = new OssAdminProxyUser();
        proxyUser.setActive(Constants.DEPARTMENT_ACTIVE);// 設置業務狀態，默認為啟用
        model.addAttribute("ossAdminProxyUser", proxyUser);
        return "proxy/add";
    }

    /**
     * 添加操作
     *
     * @param OssAdminProxyUser 代理信息
     * @param model
     * @param result
     * @return
     */
    @RequestMapping(params = "method=add", method = RequestMethod.POST)
    public String add(OssAdminProxyUser ossAdminProxyUser, BindingResult result, ModelMap model) {
        new ProxyusersValidator(ossAdminProxyUser, result).validate();
        if (result.hasErrors())
            return "proxy/add";
        else {
            proxyusersService.addProxyuser(ossAdminProxyUser);
            model.addAttribute("proxyusersList", proxyusersService.getAllProxyuser());
        }
        return "proxy/list";
    }

    /**
     * 修改代理信息,進入修改頁面
     *
     * @param proxyId 代理信息編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.GET)
    public String modify(String proxyId, ModelMap model) {
        OssAdminProxyUser ossAdminProxyUser = proxyusersService.getProxyuserByRecordId(proxyId);
        isNull(ossAdminProxyUser, ExceptionConstants.UNPROXY_OPERATION_EXCEPTION);
        model.addAttribute("ossAdminProxyUser", ossAdminProxyUser);
        return "proxy/modify";
    }

    /**
     * 修改代理信息,修改操作
     *
     * @param OssAdminProxyUser 代理信息
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modify", method = RequestMethod.POST)
    public String modify(OssAdminProxyUser ossAdminProxyUser, BindingResult result, ModelMap model) {
        new ProxyusersValidator(ossAdminProxyUser, result).validate();
        if (result.hasErrors())
            return "proxy/modify";
        else {
            proxyusersService.modifyProxyuser(ossAdminProxyUser);
            model.addAttribute("proxyusersList", proxyusersService.getAllProxyuser());
            return "proxy/list";
        }
    }

    /**
     * 刪除一條代理信息
     *
     * @param proxyId
     * @return
     */
    @RequestMapping(params = "method=delete")
    public String delete(String proxyId, ModelMap model, HttpServletResponse response) {
        OssAdminProxyUser ossAdminProxyUser = proxyusersService.getProxyuserByRecordId(proxyId);
        isNull(ossAdminProxyUser, ExceptionConstants.UNPROXY_OPERATION_EXCEPTION);
        proxyusersService.deleteProxyuser(ossAdminProxyUser);
        model.addAttribute("proxyusersList", proxyusersService.getAllProxyuser());
        return redirect2succ("proxy.do?method=listAll", "刪除了該條信息", model);
    }

    /**
     * 進入查看列表,查看所有代理信息
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=listAll")
    public String listAll(ModelMap model) {
        model.addAttribute("proxyusersList", proxyusersService.getAllProxyuser());
        return "proxy/list";
    }

    /**
     * 進入查看列表,查看所有有效代理信息
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=listUseful")
    public String listUseful(ModelMap model) {
        model.addAttribute("proxyusersList", proxyusersService.getUsefulProxyuser());
        return "proxy/list";
    }

    /**
     * 進入查看列表,查看所有失效的代理信息
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=listDisabled")
    public String listDisabled(ModelMap model) {
        model.addAttribute("proxyusersList", proxyusersService.getDisabledProxyusers());
        return "proxy/list";
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
    public void preValidate(OssAdminProxyUser ossAdminProxyUser, BindingResult results, HttpServletResponse response)
            throws IOException {
        new ProxyusersValidator(ossAdminProxyUser, results).validate();
        ajaxOutput(response, convert2JSONString(results.getAllErrors()));
    }
}
