package com.focustech.oss2008.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.oss2008.web.AbstractController;

@Controller
public class RedirectController extends AbstractController {
    @Autowired
    private EnvironmentParameter eParameter;

    @RequestMapping("/envirRedirect.do")
    public String envirRedirect(String sys, String redirect, String title, ModelMap model, HttpServletRequest request) {
        log.info("Enter EnvironmentRedirectAction::execute().");
        String returnUrl = "";
        if (null != sys && !"".equals(sys) && null != redirect && !"".equals(redirect)) {
            returnUrl = eParameter.getStringValue(sys.toUpperCase());
        }
        // redirect = redirect.replaceAll("%26", "&");
        model.addAttribute("redirect", returnUrl + redirect);
        model.addAttribute("title", title);
        model.addAttribute("backUrl", request.getHeader("referer"));
        log.info("Exit EnvironmentRedirectAction::execute().");
        return "environment_redirect";
    }
}
