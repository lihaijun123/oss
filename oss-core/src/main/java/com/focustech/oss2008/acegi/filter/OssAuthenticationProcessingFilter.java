package com.focustech.oss2008.acegi.filter;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import namespace.userauth._new.IkeyUserAuthAPI;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.util.WebUtils;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.acegi.IdentifyingCodeNoFoundException;
import com.focustech.oss2008.exception.acegi.LdapAuthenticationFailureException;
import com.focustech.oss2008.exception.acegi.TooSimpleLdapPasswordException;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.oss2008.service.UserService;


/**
 * <li></li>
 *
 * @author yangpeng 2008-4-14 下午02:00:21
 */
public class OssAuthenticationProcessingFilter extends AuthenticationProcessingFilter {
    public static final String SOURCE_CODE = "aut_security_source";
    /** 有效的動態密碼認證服務器 */
    private static String ACTIVE_AUTH_SERVER = "https://192.168.16.76:3973";
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("loginUserCache")
    private Cache loginUserCache;
    @Autowired
    private EnvironmentParameter environmentParameter;
    @Autowired
    private OssAdminParameterService parameterService;

    /*
     * (non-Javadoc)
     * @seeorg.acegisecurity.ui.webapp.AuthenticationProcessingFilter#attemptAuthentication(javax.servlet.http.
     * HttpServletRequest)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
        // request.getSession().setAttribute(SOURCE_CODE, request.getParameter(SOURCE_CODE));// 保存用戶請求的來源
        if (environmentParameter.getBooleanValue("needCheckValidateNumber")) {
            checkValidateNumber(request);
        }
        Authentication authentication = super.attemptAuthentication(request);
        // 記錄此次成功登陸的信息為最後一次登陸信息，並記錄登陸日志
        if (null != authentication.getPrincipal() && authentication.getPrincipal() instanceof OssAdminUser
                && authentication.isAuthenticated()) {
            OssAdminUser user = (OssAdminUser) authentication.getPrincipal();
            Date start = new Date();
            //checkDynPassword(request, user);
            //System.err.println("動態密碼認證整個過程的時間(ms)︰" + (new Date().getTime() - start.getTime()));
            userService.saveLoginLog(user, request.getRemoteAddr(), StringUtils.isEmpty(request
                    .getParameter(SOURCE_CODE)) ? "SAL" : request.getParameter(SOURCE_CODE), request.getRemoteUser());
        }
        else {
            super.logger.error("the login user is not authenticated!");
        }
        //
        return authentication;
    }

    /**
     * 檢查驗證碼
     */
    protected void checkValidateNumber(HttpServletRequest request) {
        String strRandom = (String) WebUtils.getSessionAttribute(request, "strRandom");
        if (super.getFilterProcessesUrl().equals(request.getRequestURI()) && !StringUtils.isEmpty(strRandom)
                && !strRandom.equalsIgnoreCase(request.getParameter("validateNumber"))) {
            String username = obtainUsername(request);
            request.getSession().setAttribute(ACEGI_SECURITY_LAST_USERNAME_KEY, username);
            throw new IdentifyingCodeNoFoundException(MessageUtils
                    .getExceptionValue(ExceptionConstants.VALIDATE_UNCORRECT_EXCEPTION));
        }
    }

    /**
     * 驗證動態密碼
     *
     * @param request 頁面請求的數據
     * @param user 用戶信息
     */
    protected void checkDynPassword(HttpServletRequest request, OssAdminUser user) {
        boolean isCheckDynPassword = environmentParameter.getBooleanValue("needCheckDynPassword", true);
        String dynPassword = StringUtils.trim(request.getParameter("dynPassword"));
        if (!isCheckDynPassword && StringUtils.isEmpty(dynPassword)) {
            // 當不強制使用動態密碼驗證時，如果密碼不填寫，則不驗證
            return;
        }
        if (!OssAdminUser.DYN_PASSWORD_ENABLE.equals(user.getDynPasswordFlag())) {
            // 當用戶個體不要求使用動態密碼驗證時，則不驗證
            return;
        }
        String userName = StringUtils.trim(user.getUsername());
        if (StringUtils.isEmpty(dynPassword)) {
            throw new IdentifyingCodeNoFoundException(MessageUtils
                    .getExceptionValue("acegi.authentication.dyn.password.empty.exception"));
        }
        if (StringUtils.isEmpty(ACTIVE_AUTH_SERVER)) {
            ACTIVE_AUTH_SERVER = getActiveServer();
        }
        String filePath = "WEB-INF" + File.separator + "config" + File.separator + "store" + File.separator;
        String keyStoreFilePath =
                ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("")
                        + environmentParameter.getStringValue("acegi.dyn.password.keystore.file", filePath
                                + "client.keystore");
        String trustStoreFilePath =
                ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("")
                        + environmentParameter.getStringValue("acegi.dyn.password.truststore.file", filePath
                                + "client.truststore");
        String keyStorePassword =
                environmentParameter.getStringValue("acegi.dyn.password.keystore.password", "focus66677777");
        String trustStorePassword =
                environmentParameter.getStringValue("acegi.dyn.password.truststore.password", "focus66677777");
        String domain = environmentParameter.getStringValue("dynPasswordUsersHaveDomains", "windows");
        if (StringUtils.isEmpty(domain)) {
            throw new IdentifyingCodeNoFoundException(MessageUtils
                    .getExceptionValue("acegi.authentication.dyn.nodomain.exception"));
        }
        try {
            // 設置認證服務器
            IkeyUserAuthAPI.setendpoint(ACTIVE_AUTH_SERVER);
            System.err.println("動態密碼使用的認證服務器地址︰" + ACTIVE_AUTH_SERVER);
            System.setProperty("javax.net.ssl.keyStore", keyStoreFilePath); // 加載keystore
            System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword); // keystore 密碼
            System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);// 加載truststore
            System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword); // truststore密碼
            // 認證類型 0- 認證賬戶 2-令牌序列號（目前支持這兩種認證方式）
            short accountType = (short) environmentParameter.getIntValue("acegi.dyn.password.account_type", 0);
            // 是否驗證pin碼 0 — 不認證PIN碼 1— 認證PIN碼
            short isAuthPin = (short) environmentParameter.getIntValue("acegi.dyn.password.is_auth_pin", 0);
            // 是否強制認證動態密碼，默認填0
            short isForceAuth = (short) environmentParameter.getIntValue("acegi.dyn.password.is_force_auth", 0);
            // 如果是校正令牌，則進行令牌校正。如果校正通過，則認為登錄成功，直接return；如果失敗，拋出異常以終止下面的操作
            if (adjustToken(request, accountType, userName, dynPassword) > 0) {
                return;
            }
            String[] domains = domain.split(",");
            // 返回認證的結果
            int flag = 0;
            // 對用戶在不同域上進行認證
            for (String dom : domains) {
                dom = StringUtils.trim(dom);
                if (StringUtils.isEmpty(dom)) {
                    continue;
                }
                String accountName = userName + "." + dom;
                flag = IkeyUserAuthAPI.ikey_auth(accountType, isAuthPin, isForceAuth, accountName, dynPassword);
                if (flag > 0) {
                    // 認證成功，則跳出
                    break;
                }
                else if (flag == -1) {
                    // 動態密碼錯誤
                    throw new IdentifyingCodeNoFoundException(MessageUtils
                            .getExceptionValue("acegi.authentication.dyn.unsucc.exception"));
                }
                else if (flag == -6 || flag == -15 || flag == -16) {
                    // 鎖定狀態或一分鐘內重復認證
                    throw new IdentifyingCodeNoFoundException(MessageUtils
                            .getExceptionValue("acegi.authentication.dyn.duplication.exception"));
                }
                else if (flag == -229) {
                    // 需要校正
                    request.getSession().setAttribute("lastPassword", dynPassword);
                    request.getSession().setAttribute("domain", dom);
                    throw new IdentifyingCodeNoFoundException(MessageUtils
                            .getExceptionValue("acegi.authentication.dyn.need_ajust.exception"));
                }
            }
            if (flag == -4) {
                // 賬戶不存在
                throw new IdentifyingCodeNoFoundException(MessageUtils
                        .getExceptionValue("acegi.authentication.dyn.unsucc.exception"));
            }
            else if (flag < 0) {
                // 其它認證不成功的情況，如賬戶凍結、應用暫停等
                throw new IdentifyingCodeNoFoundException(MessageUtils
                        .getExceptionValue("acegi.authentication.dyn.auth_failed.exception"));
            }
        }
        catch (IdentifyingCodeNoFoundException ex) {
            throw ex;
        }
        catch (Exception e) {
            System.err.println("認證服務失敗！尋找可用的認證服務器！");
            Date start = new Date();
            ACTIVE_AUTH_SERVER = getActiveServer();
            System.err.println("獲取認證服務器地址所需要時間（ms）︰" + (new Date().getTime() - start.getTime()));
            super.logger.error("the dynamic password auth failed!", e);
            throw new IdentifyingCodeNoFoundException(MessageUtils.getExceptionValue("default.exception.message")
                    + MessageUtils.getExceptionValue("acegi.authentication.dyn.auth_failed.exception"));
        }
    }

    /**
     * 獲取有效的認證服務器。如果沒有可用的服務器，則拋出異常！
     *
     * @return
     */
    private String getActiveServer() {
        String result = "";
        List<OssAdminParameter> params = parameterService.listParameters("PARAM_TYPE_DYN_PASSWORD_AUTH_SERVER");
        String servers = "";
        if (params != null && params.size() > 0) {
            servers = params.get(0).getParameterKey();
            System.err.println("配置中的認證服務器有︰" + servers);
        }
        else {
            servers = "https://192.168.16.71:3973";
        }
        if (StringUtils.isEmpty(StringUtils.trim(servers))
                || StringUtils.isEmpty(StringUtils.trim(servers.replaceAll(",", "")))) {
            throw new IdentifyingCodeNoFoundException(MessageUtils
                    .getExceptionValue("acegi.authentication.dyn.noserver.exception"));
        }
        int timeOut = environmentParameter.getIntValue("acegi.dyn.password.authentication.server.timeout", 10000);
        int soTimeOut = environmentParameter.getIntValue("acegi.dyn.password.authentication.server.sotimeout", 10000);
        // 先判斷當前服務器是否可用
        if (isConnect(ACTIVE_AUTH_SERVER, timeOut, soTimeOut)) {
            return ACTIVE_AUTH_SERVER;
        }
        else {
            // 如果這個不能連接，則將其從判斷的隊列中取消
            servers = servers.replaceAll(ACTIVE_AUTH_SERVER, "");
        }
        for (String server : servers.split(",")) {
            if (StringUtils.isEmpty(StringUtils.trim(server))) {
                continue;
            }
            if (isConnect(server, timeOut, soTimeOut)) {
                result = server;
                break;
            }
        }
        if (StringUtils.isEmpty(result)) {
            throw new IdentifyingCodeNoFoundException(MessageUtils
                    .getExceptionValue("acegi.authentication.dyn.noserver.exception"));
        }
        return result;
    }

    /**
     * 判斷服務是否可以連接，即是否可用。
     *
     * @param server 服務器完整連接方式，如︰https://192.168.16.76:3973
     * @param timeOut HTTP超時時間
     * @param soTimeOut 數據包超時時間
     * @return 返回驗證結果
     */
    private boolean isConnect(String server, int timeOut, int soTimeOut) {
        server = StringUtils.defaultIfEmpty(StringUtils.trim(server), "");
        String[] parts = server.split(":");
        if (parts.length != 3) {
            return false;
        }
        Socket s = new Socket();
        try {
            String serverIp = parts[1].replaceAll("//", "");
            int port = Integer.parseInt(parts[2].trim());
            s.connect(new InetSocketAddress(serverIp, port), timeOut);// 設置連接時間超時
            s.setSoTimeout(soTimeOut); // 設置讀取時間超時
            if (s.isConnected()) {
                // 成功
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            // 失敗
            return false;
        }
    }

    /**
     * 首先確定是否需要校正，如果需要，則校正；如果校正失敗，則清除當前的記錄，讓用戶重新進行校正操作；<br>
     * 其它校正情況直接返回校正結果
     *
     * @param request 頁面請求
     * @param accountType 賬戶類型
     * @param userName 賬戶名
     * @param dynPassword 當前輸入的動態密碼
     * @return 返回校正結果，其中︰<br>
     *         0 - 表示沒有進行校正； 1 - 表示校正成功；
     */
    protected int adjustToken(HttpServletRequest request, short accountType, String userName, String dynPassword) {
        HttpSession session = request.getSession();
        int adjustResult = 0;
        String lastPassword =
                session.getAttribute("lastPassword") == null ? "" : String
                        .valueOf(session.getAttribute("lastPassword"));
        if (StringUtils.isEmpty(lastPassword)) {
            // 如果沒有記錄上次密碼，表示不是校正要求，直接退出校正程序
            return adjustResult;
        }
        try {
            String domain =
                    session.getAttribute("domain") == null ? "windows" : String.valueOf(session.getAttribute("domain"));
            userName += "." + domain;
            adjustResult = IkeyUserAuthAPI.ikey_token_adjust(accountType, userName, lastPassword, dynPassword);
            // 如果校正成功，將成功標志置為1，便于區別
            if (adjustResult == 0) {
                adjustResult = 1;
            }
            else if (adjustResult < 0) {
                // 校正失敗，將Session清除；同時拋出異常提示相應信息
                handleAdjustError(session, adjustResult);
            }
        }
        catch (IdentifyingCodeNoFoundException ex) {
            throw ex;
        }
        catch (Exception e) {
            super.logger.error(e);
            handleAdjustError(session, 0);
        }
        return adjustResult;
    }

    /**
     * 校正失敗時的處理操作。消除session內容，拋出相應提示
     *
     * @param session
     * @param result 校正失敗的結果 0:發生異常;-1:動態密碼錯誤;其它:認證賬戶或應用有問題
     */
    private void handleAdjustError(HttpSession session, int result) {
        session.removeAttribute("lastPassword");
        session.removeAttribute("domain");
        String message = "";
        if (result == 0) {
            message =
                    MessageUtils.getExceptionValue("acegi.authentication.dyn.ajust_exception_failed.exception")
                            + MessageUtils.getExceptionValue("acegi.dyn.password.adjust.site");
        }
        else if (result == -1) {
            message =
                    MessageUtils.getExceptionValue("acegi.authentication.dyn.ajust_password_failed.exception")
                            + MessageUtils.getExceptionValue("acegi.dyn.password.adjust.site");
        }
        else {
            message = MessageUtils.getExceptionValue("acegi.authentication.dyn.ajust_account_failed.exception");
        }
        throw new IdentifyingCodeNoFoundException(message);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request) {
        String url = super.determineTargetUrl(request);
        String source = request.getParameter(SOURCE_CODE);
        if (StringUtils.isNotEmpty(source)) {
            if (StringUtils.indexOf(url, '?') > -1) {
                url = url.concat("&").concat(SOURCE_CODE).concat("=").concat(source);
            }
            else {
                url = url.concat("?").concat(SOURCE_CODE).concat("=").concat(source);
            }
        }
        return url;
    }

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.ui.AbstractProcessingFilter#determineFailureUrl(javax.servlet.http.HttpServletRequest,
     * org.acegisecurity.AuthenticationException)
     */
    @Override
    protected String determineFailureUrl(HttpServletRequest request, AuthenticationException failed) {
        String url = super.determineFailureUrl(request, failed);
        String source = request.getParameter(SOURCE_CODE);
        if (StringUtils.isNotEmpty(source)) {
            if (StringUtils.indexOf(url, '?') > -1) {
                url = url.concat("&").concat(SOURCE_CODE).concat("=").concat(source);
            }
            else {
                url = url.concat("?").concat(SOURCE_CODE).concat("=").concat(source);
            }
        }
        return url;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.acegisecurity.ui.AbstractProcessingFilter#onSuccessfulAuthentication(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, org.acegisecurity.Authentication)
     */
    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            Authentication authResult) throws IOException {
        if (null != authResult.getPrincipal() && authResult.getPrincipal() instanceof OssAdminUser) {
            OssAdminUser user = (OssAdminUser) authResult.getPrincipal();
            if (!loginUserCache.isKeyInCache(user.getLoginName())) {
                loginUserCache.put(new Element(user.getLoginName(), user));
            }
        }
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException {
        if (failed instanceof BadCredentialsException || failed instanceof UsernameNotFoundException
                || failed instanceof LdapAuthenticationFailureException
                || failed instanceof TooSimpleLdapPasswordException) {
            // 登陸失敗
            Authentication authentication = failed.getAuthentication();
            String name = "";
            String password = "";
            if (authentication != null) {
                name = authentication.getName();
                password = String.valueOf(authentication.getCredentials());
            }
            String remark = failed.getMessage() + ";password:" + password;
            userService.saveLoginFailed(StringUtils.isEmpty(request
                    .getParameter(OssAuthenticationProcessingFilter.SOURCE_CODE)) ? "SAL" : request
                    .getParameter(OssAuthenticationProcessingFilter.SOURCE_CODE), name, request.getRemoteAddr(),
                    ((HttpServletRequest) request).getRemoteUser(), remark);
        }
        //
        super.onUnsuccessfulAuthentication(request, response, failed);
    }

    public static String getACTIVE_AUTH_SERVER() {
        return ACTIVE_AUTH_SERVER;
    }

    public static void setACTIVE_AUTH_SERVER(String active_auth_server) {
        ACTIVE_AUTH_SERVER = active_auth_server;
    }
}
