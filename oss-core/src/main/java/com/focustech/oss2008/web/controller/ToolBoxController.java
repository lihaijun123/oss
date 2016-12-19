package com.focustech.oss2008.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.exception.service.PasswordInvalidException;
import com.focustech.oss2008.model.Category;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.ToolBoxService;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.oss2008.web.validator.UserValidator;

/**
 * <li>工具箱控制類。功能︰修改自己信息、顯示目錄編碼</li>
 *
 * @author taofucheng 2008-9-9 下午03:59:52
 */
@Controller
@RequestMapping("/toolBox.do")
public class ToolBoxController extends AbstractController {
    @Autowired
    private UserService userService;
    @Autowired
    private ToolBoxService toolBoxService;

    /**
     * 進入用戶修改自己信息界面
     *
     * @param userId 用戶編號
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modifySelf", method = RequestMethod.GET)
    public String modifySelf(String userId, ModelMap model) {
        String curUserId = getLoginUser().getUserId();
        if (!curUserId.equals(userId)) {
            return redirect2succ(null, MessageUtils.getExceptionValue(ExceptionConstants.FAILED_MODIFY_OTHER_INFO),
                    model);
        }
        OssAdminUser user = userService.getUserById(userId);
        isNull(user, ExceptionConstants.UNUSER_OPERATION_EXCEPTION);
        model.addAttribute("ossAdminUser", user);
        return "tool_box/modify_yourself";
    }

    /**
     * 用戶修改信息操作
     *
     * @param pwdDisplayCtrl 標志用戶是否選擇更改密碼
     * @param oldPwd 原來密碼
     * @param user 用戶更改信息
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(params = "method=modifySelf", method = RequestMethod.POST)
    public String modifySelf(String pwdDisplayCtrl, String oldPwd, OssAdminUser user, BindingResult result,
            ModelMap model) {
        OssAdminUser user1 = userService.getUserById(user.getUserId());
        user.setOssAdminDepartment(user1.getOssAdminDepartment());
        if (pwdDisplayCtrl != null) {
            if (!user1.getPassword().equals(oldPwd)) {
                model.addAttribute("message", MessageUtils.getExceptionValue(ExceptionConstants.FAILED_MODIFY)
                        + MessageUtils.getExceptionValue(ExceptionConstants.FAILED_MODIFY_PASSWORD_INCORRECT));
                return "tool_box/modify_yourself";
            }
        }
        try {
            userService.modifyUserSelf(user);
        }
        catch (PasswordInvalidException e) {
            result.rejectValue("password", UserValidator.ERROR_CODE_REQUIRED, e.getMessage());
            return "tool_box/modify_yourself";
        }
        model.addAttribute("message", MessageUtils.getExceptionValue(ExceptionConstants.SUCC_MODIFY));
        return "tool_box/modify_yourself";
    }

    /**
     * 顯示目錄信息樹
     *
     * @author lijun
     * @return
     */
    @RequestMapping(params = "method=categoryTree")
    public String categoryTree(ModelMap model) {
        HashMap<String, List<Category>> category = toolBoxService.Category(Constants.SITE_TYPE_MIC_EN);
        model.addAttribute("main", category.get("main"));
        model.addAttribute("title", category.get("title"));
        return "tool_box/category_treelist";
    }
}
