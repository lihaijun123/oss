package com.focustech.oss2008.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.DisabledException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.focustech.common.constant.OverallConst;
import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.acegi.ldap.LdapService;
import com.focustech.oss2008.exception.acegi.IdentifyingCodeNoFoundException;
import com.focustech.oss2008.exception.acegi.LdapAccountExpiredException;
import com.focustech.oss2008.exception.acegi.TooSimpleLdapPasswordException;
import com.focustech.oss2008.exception.service.LdapCheckedException;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.service.BulletinService;
import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.oss2008.service.MenuTree;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.web.AbstractController;

/**
 * <li>登陸Action</li>
 *
 * @author yangpeng 2008-3-25 下午05:03:24 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@Controller
public class LoginController extends AbstractController {
    @Autowired
    private MenuTree menuTree;
    @Autowired
    private EnvironmentParameter eParameter;
    @Autowired
    private BulletinService bulletinService;
    @Autowired
    private LdapService ldapService;
    @Autowired
    private RoleService roleService;

    // @Autowired
    // private FocusBIService focusBIService;

    @RequestMapping(value = "/logon.do")
    public String logon(String aut_security_source, Model model, HttpServletRequest request, WebRequest webRequest) {
        if (isLoginSuccessful()) {
            // 存在權限信息並已被 認證過,直接顯示主頁面
            return workspace(aut_security_source, model, webRequest);
        }
        else {
            if (StringUtils.isEmpty(aut_security_source)) {
                aut_security_source = "SAL";
            }
            //
            String serverName = request.getServerName().toLowerCase();
            String refererName = request.getHeader("Referer");
            /*String agtServerName = getServerName("AGT");
            String sgsServerName = getServerName("SGS");
            //
            refererName = refererName == null ? "" : refererName.toLowerCase();
            agtServerName = agtServerName.toLowerCase();
            sgsServerName = sgsServerName.toLowerCase();
            //
            if (serverName.equals(agtServerName)
                    || (StringUtils.isNotEmpty(refererName) && (refererName.indexOf(agtServerName) >= 0))
                    || "AGT".equals(aut_security_source)) {
                model.addAttribute("isAgt", true);
                model.addAttribute("isSgs", false);
                aut_security_source = "AGT";
            }
            else if (serverName.equals(sgsServerName)
                    || (StringUtils.isNotEmpty(refererName) && (refererName.indexOf(sgsServerName) >= 0))
                    || "SGS".equals(aut_security_source)) {
                model.addAttribute("isAgt", false);
                model.addAttribute("isSgs", true);
                aut_security_source = "SGS";// 如果使用SGS系統的域名，強制轉換到代理商系統中訪問
            }
            else {
                model.addAttribute("isAgt", false);
                model.addAttribute("isSgs", false);
            }
            */
            //
            String autSecurityName = eParameter.getStringValue(aut_security_source + "_NAME");
            model.addAttribute("aut_security_name", autSecurityName);
            model.addAttribute("aut_security_source", aut_security_source);
            return "login";
        }
    }

    @RequestMapping("/workspace.do")
    public String workspace(String aut_security_source, Model model, WebRequest request) {
        aut_security_source = StringUtils.isEmpty(aut_security_source) ? "SAL" : aut_security_source;
        model.addAttribute("aut_model", aut_security_source);
        model.addAttribute("needShow", bulletinService.hasNewActiveBulletin(aut_security_source));
        long ldapValidDay = 0;
        if (getLoginUser().isLdapUser()) {
            String key = "oss_user_ldap_valid_day_" + getLoginUser().getUserId();
            Object o = request.getAttribute(key, WebRequest.SCOPE_SESSION);
            if ((null != o) && (o instanceof Long)) {
                ldapValidDay = ((Long) o).longValue();
            }
            else {
                try {
                    ldapValidDay = ldapService.getLdapValidDay(getLoginUser().getLoginName());
                    request.setAttribute(key, ldapValidDay, WebRequest.SCOPE_SESSION);
                }
                catch (LdapCheckedException e) {
                    // 默認以-1為錯誤數據
                    log.error(e.getMessage());
                    ldapValidDay = -1;
                }
            }
        }
        if (StringUtils.isEmpty(aut_security_source) || "SAL".equalsIgnoreCase(aut_security_source)) {
            String info = "您好，" + getLoginUser().getFullname();
            if (getLoginUser().isLdapUser()) {
                info += "，你的帐号还有<font color='red'>" + ldapValidDay + "</font>天到期！";
            }
            model.addAttribute("userId", getLoginUser().getUserId());
            model.addAttribute("info", info);

            String loginUserRoleId = getLoginUserRoleId();
            List<OssAdminResource> resoruces = roleService.getResourceByRole(TCUtil.lv(loginUserRoleId), ResourceService.RESOURCE_TYPE_WORKSPACE);
            model.addAttribute("workspaceTabs", resoruces);
            return "workspace";
        }
        else {
            // 跳轉到其他系統的主頁面
            String domain = eParameter.getStringValue(aut_security_source);
            if (domain == null) {
                domain = "";
            }
            if (!domain.endsWith("/")) {
                domain += "/";
            }
            return redirectTo(domain + "showMain.do?aut_security_source=" + aut_security_source);
        }
    }

    @RequestMapping("/showmenu.do")
    public void showMenu(HttpServletResponse response) throws IOException {
        // String roleName = getRoleNameOfLoginUser();
        String menu = menuTree.getMenu();
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(menu);
        response.getWriter().flush();
    }
    @RequestMapping("/showmenu2.do")
    public void showMenu2(HttpServletResponse response) throws IOException {

    	// String roleName = getRoleNameOfLoginUser();
    	String menu = menuTree.getMenu2();
    	response.setContentType("text/html; charset=UTF-8");
    	response.getWriter().write(menu);
    	response.getWriter().flush();
    }

    @RequestMapping("/login_error.do")
    public String loginError(String aut_security_source, Model model, WebRequest request) {
        RuntimeException exception =
                (RuntimeException) request.getAttribute("ACEGI_SECURITY_LAST_EXCEPTION", WebRequest.SCOPE_SESSION);
        String errorMessage = null;
        boolean isLdapUser = false;
        if ((null != exception)
                && ((exception instanceof IdentifyingCodeNoFoundException) || (exception instanceof DisabledException))) {
            errorMessage = exception.getMessage();
        }
        else if ((null != exception) && (exception instanceof TooSimpleLdapPasswordException)) {
            isLdapUser = true;
            errorMessage = "用户密码过于简单，请通过“修改域帐户密码”功能进行修改！";
        }
        else if ((null != exception) && (exception instanceof LdapAccountExpiredException)) {
            LdapAccountExpiredException ldapAccountExpiredException = (LdapAccountExpiredException) exception;
            if (ldapAccountExpiredException.getRemainDay() >= 0) {
                isLdapUser = true;
                errorMessage = "对不起，您的帐号还有" + ldapAccountExpiredException.getRemainDay() + "天过期，请立刻修改域帐号密码！";
            }
            else {
                errorMessage = "对不起，您的域帐号已经过期，请与系统支持部联系！";
            }
        }
        else if ((null != exception) && (exception instanceof AuthenticationException)) {
            errorMessage = "用户不存在或密码不正确或用户已经被禁用!";
        }
        //
        request.removeAttribute("ACEGI_SECURITY_LAST_EXCEPTION", WebRequest.SCOPE_SESSION);
        if (errorMessage != null) {
            model.addAttribute(errorMessage);
        }
        //
        String serverName = "";
        if (request instanceof ServletWebRequest) {
            serverName = ((ServletWebRequest) request).getRequest().getServerName();
        }
        /*
        if (StringUtils.isNotEmpty(serverName)) {
            serverName = serverName.toLowerCase();
            String agtServerName = getServerName("AGT");
            String sgsServerName = getServerName("SGS");
            agtServerName = agtServerName.toLowerCase();
            sgsServerName = sgsServerName.toLowerCase();
            //
            //
            if (serverName.indexOf(agtServerName) >= 0) {
                model.addAttribute("isAgt", true);
                model.addAttribute("isSgs", false);
                aut_security_source = "AGT";
            }
            else if (serverName.indexOf(sgsServerName) >= 0) {
                model.addAttribute("isAgt", false);
                model.addAttribute("isSgs", true);
                aut_security_source = "SGS";
            }
            else {
                model.addAttribute("isAgt", false);
                model.addAttribute("isSgs", false);
            }
        }
        */
        if (StringUtils.isEmpty(aut_security_source)) {
            aut_security_source = "SAL";
        }
        String autSecurityName = eParameter.getStringValue(aut_security_source + "_NAME");
        model.addAttribute("aut_security_name", autSecurityName);
        model.addAttribute("aut_security_source", aut_security_source);
        model.addAttribute("isLdapUser", isLdapUser);
        return "login";
    }

    @RequestMapping("/error.do")
    public void error(Model model, WebRequest request, String message) {
        if (!StringUtils.isEmpty(message)) {
            model.addAttribute("exception", new Exception(message));
        }
        else if (null != request.getAttribute("ACEGI_SECURITY_403_EXCEPTION", WebRequest.SCOPE_REQUEST)) {
            model.addAttribute("exception", request.getAttribute("ACEGI_SECURITY_403_EXCEPTION",
                    WebRequest.SCOPE_REQUEST));
        }
    }

    @RequestMapping(value = "/lisModel.do")
    public void changeListModel(String flag, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if ("1".equals(flag)) {
            request.getSession().setAttribute("SF_K", "1");
        }
        else {
            request.getSession().removeAttribute("SF_K");
        }
        response.getWriter().write("1");
    }

    protected String getServerName(String serverNameKey) {
        String serverName = eParameter.getStringValue(serverNameKey).toLowerCase();
        if (serverName.startsWith(OverallConst.WEB_PROTOCOL_HTTP)) {
            serverName =
                    serverName.substring(serverName.indexOf(OverallConst.WEB_PROTOCOL_HTTP)
                            + OverallConst.WEB_PROTOCOL_HTTP.length(), serverName.length() - 1);
        }
        return serverName;
    }

    /**
     * 調用BI系統功能。轉發請求BI系統中的功能。<br>
     *
     * <pre>
     * 如果需要請求BI中的鏈接(真正的請求鏈接，請與BI項目組聯系)︰
     * <i>http://bi.vemic.com/pentaho/ViewAction?solution=12_mic_area_service_statistic_090703&path=reports&action=client_assign_stat.xaction</i>，
     * 則在這里應該寫成︰
     * <i>http://sales.vemic.com/fbi.do?request=ViewAction?solution=12_mic_area_service_statistic_090703%26path=reports&action=client_assign_stat.xaction</i>
     * </pre>
     *
     * @param request 需要請求BI的具體鏈接。注意︰&用%26表示
     * @param model 頁面緩存
     * @return 返回進入的頁面
     */
    @RequestMapping("/fbi.do")
    public String fbi(String request, ModelMap model) {
        log.info("Enter EnvironmentRedirectAction::execute().");
        String returnUrl = "";
        if (StringUtils.isNotEmpty(request)) {
            returnUrl = eParameter.getStringValue("FBI", "http://localhost/").toLowerCase();
            returnUrl = returnUrl.endsWith("/") ? returnUrl : returnUrl + "/";
            // 將鏈接中的轉義符號替換為真正的請求符號
            request = request.replaceAll("%26", "&");
            // request = request.replaceAll("%3F", "?");
            request += getSpecialParams(request, getLoginUser().getUserId());
        }
        model.addAttribute("redirect", returnUrl + request);
        model.addAttribute("title", "BI系統");
        log.info("Exit EnvironmentRedirectAction::execute().");
        return "environment_redirect";
    }

    /** 獲取與BI系統進行通信的特殊參數 */
    protected String getSpecialParams(String requestPath, String userId) {
        String result = "&_request_source_=oss&_request_confirm_=";
        if (requestPath.indexOf("?") == -1) {
            // 如果沒有問號標志，則這里應該以問號連接
            result = "?_request_source_=oss&_request_confirm_=";
        }
        String flagKey = "";
        // flagKey = focusBIService.pushRequestFlag(userId);
        // // 將用戶的請求信息記錄到PTL中，便于BI系統的確認。同時返回對應的變量值
        // if (StringUtils.isEmpty(flagKey)) {
        // // 如果設置失敗，則再設置一次。如果再失敗了，就不管了。
        // flagKey = focusBIService.pushRequestFlag(userId);
        // }
        return result + flagKey;
    }
}
