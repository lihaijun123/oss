package com.focustech.oss2008.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.exception.mvc.NoSuchObjectException;
import com.focustech.oss2008.exception.mvc.UnSuccessfulLoginException;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.oss2008.web.tags.impls.FocusFormTag;

/**
 * <li>Controller基类</li>
 * <p>
 * 为继承他的controller提供公共方法
 * </p>
 * <b>方法1</b>:日志记录器。可直接在子类中调用<b>log.error("samples");</b>这样的代码书写日志。
 * <p>
 * <b>方法2</b>:系统参数服务。使用自动注入的<b>BaseParametersService</b>得到需要的参数。
 * </p>
 *
 * @see Log
 * @see OssAdminParameterService
 * @author yangpeng 2008-4-17 上午10:29:06
 */
public abstract class AbstractController {
    protected Logger log = LoggerFactory.getLogger(AbstractController.class);
    /**编码utf-8*/
    protected final static String  ENCODE_UTF8 = "UTF-8";
    /**response.contentType 类型*/
    protected final static String  CONTENT_TYPE_HTML = "text/html; charset=UTF-8";
    /** 参数服务,供基类使用 */
    @Autowired
    protected OssAdminParameterService parametersService;

    /**
     * 返回默认的错误页面
     */
    protected String jump2DefaultErrorPage() {
        return "error";
    }

    /**
     * 返回默认的错误页面及需要显示的消息
     */
    protected String jump2DefaultErrorPage(String message) {
        try {
            message = URLEncoder.encode(message, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            return redirectTo("error.do?message=" + e.getMessage());
        }
        return redirectTo("error.do?message=" + message);
    }

    /**
     * 将目标java对象转换成JSON格式的String
     */
    protected String convert2JSONString(Object object) {
        JSONArray jsonObject = JSONArray.fromObject(object);
        return jsonObject.toString();
    }

    /**
     * 取当前登录用户信息
     *
     * @return 当前登录用户对象
     * @throws UnSuccessfulLoginException 用户未登录，登录信息为空
     * @see OssAdminUsers
     */
    protected OssAdminUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? null : authentication.getPrincipal();
        if (null != principal && authentication.isAuthenticated()) {
            return (OssAdminUser) principal;
        }
        else {
            throw new UnSuccessfulLoginException();
        }
    }
    /**
     * 登录用户id
     * *
     * @return
     */
    protected String getLoginUserId(){
    	OssAdminUser loginUser = getLoginUser();
    	return loginUser.getUserId();
    }

    /**
     * 登录用户角色id
     */
    protected String getLoginUserRoleId() {
        Set<OssAdminRole> roles = getLoginUser().getRoles();
        String roleId = "";
        if (roles != null) {
            for (OssAdminRole role : roles) {
                roleId = String.valueOf(role.getRoleId());
                break;
            }
        }
        return roleId;
    }

    /**
     * 登陆用户角色名称
     */
    protected String getRoleNameOfLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.isAuthenticated()) {
            return authentication.getAuthorities()[0].getAuthority();
        }
        else {
            throw new UnSuccessfulLoginException();
        }
    }

    /**
     * 判断当前用户是否登录成功
     *
     * @return
     */
    protected boolean isLoginSuccessful() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? null : authentication.getPrincipal();
        if (null != principal && authentication.isAuthenticated()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 用于redirect到某个页面
     *
     * @param url 目标url
     * @return 增加redirect处理过的url
     */
    public String redirectTo(String url) {
        if (StringUtils.isEmpty(url) || url.startsWith("redirect:")) {
            return url;
        }
        else {
            return "redirect:" + url;
        }
    }

    @SuppressWarnings("deprecation")
    public String redirectTo(String url, HttpServletRequest request) {
        String hidReferer = request.getParameter(FocusFormTag.REQ_HID_PARAM_ITEM);
        if (hidReferer != null) {
            if (!url.endsWith("&")) {
                url += "&";
            }
            url += FocusFormTag.REQ_HID_PARAM_ITEM + "=" + URLEncoder.encode(hidReferer);
        }
        return redirectTo(url);
    }

    /**
     * 用于forward到某个页面
     *
     * @param url 目标url
     * @return 增加redirect处理过的url
     */
    public String forwardTo(String url) {
        if (StringUtils.isEmpty(url) || url.startsWith("forward:")) {
            return url;
        }
        else {
            return "forward:" + url;
        }
    }

    /**
     * 判断当前对象是否为空，如果为空抛异常
     *
     * @param bean 当前对象
     * @param messageConstants 错误信息常量
     * @throws NoSuchObjectException 对象为空
     */
    protected void isNull(Object object, String messageConstants) {
        if (object == null) {
            throw new NoSuchObjectException(MessageUtils.getExceptionValue(messageConstants));
        }
    }

    /**
     * ajax输出
     *
     * @param response HttpServletResponse
     * @param outputString 输出字符
     * @throws IOException 输出流异常
     */
    protected void ajaxOutput(HttpServletResponse response, String outputString) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(outputString);
        response.getWriter().flush();
    }

    /**
     * 操作完成后指定的转向的页面
     *
     * @param url 页面转向到指定URL(当操作窗口不是EXT生成的弹出框时，该项不能为空；当操作窗口是EXT生成的弹出框时，并希望弹出框不消失，则该项不能为null)
     * @param message 提示信息（这个可以为null）
     * @param model
     * @return
     */
    protected String redirect2succ(String url, String message, ModelMap model) {
        model.addAttribute("url", url);
        model.addAttribute("view", message);
        return "succ";
    }

    /**
     * 得到当前登录用户的ip
     *
     * @return
     */
    protected String getLoginIp() {
        if (isLoginSuccessful()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object credentials = authentication == null ? null : authentication.getDetails();
            if (credentials instanceof WebAuthenticationDetails) {
                return ((WebAuthenticationDetails) credentials).getRemoteAddress();
            }
            else {
                throw new UnSuccessfulLoginException();
            }
        }
        else {
            throw new UnSuccessfulLoginException();
        }
    }

    /**
     * 保存上次请求的url,将其放到session中
     *
     * @param saveName
     * @param request
     */
    protected void saveBackUrl(String saveName, HttpServletRequest request) {
        String backUrl = request.getHeader("referer");
        request.getSession().setAttribute(saveName, backUrl);
    }

    /**
     * 判断是否为列表过来
     *
     * @param request
     * @return
     */
    protected String rememberBackUrl(HttpServletRequest request) {
        String backUrl = request.getHeader("referer");
        if (backUrl != null && backUrl.indexOf("uitoolList") > 0) {
            return backUrl;
        }
        return null;
    }
    /**
     * 错误信息组装为json格式进行输出
     * @param allErrors
     * @return
     */
    protected JSONObject convertErrorsToJson(List<FieldError> allErrors) {
    	JSONObject jsonObject = new JSONObject();
    	if(null != allErrors){
    		for(FieldError error : allErrors){
    			jsonObject.put(error.getField(), error.getDefaultMessage());
    		}
    	}
		return jsonObject;
	}
    /**
     *
     * *
     * @param content
     * @param isTrue
     * @return
     */
    public String getMessage(String content, boolean isTrue){
    	return "<span class='label label-" + (isTrue ? "success" : "danger") + "'>" + content + "</span>";
    }
}
