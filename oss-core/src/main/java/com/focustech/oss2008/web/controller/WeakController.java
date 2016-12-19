package com.focustech.oss2008.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.oss2008.web.AbstractController;

/**
 * <li>登陸Action</li>
 *
 * @author yangpeng 2008-3-25 下午05:03:24 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@RequestMapping("/weak.do")
@Controller
public class WeakController extends AbstractController {
    @RequestMapping(params = "method=view", method = RequestMethod.GET)
    public String showWeak(Model model) throws IOException {
        return "weakup/weak";
    }

    @RequestMapping(params = "method=guest", method = RequestMethod.GET)
    public String showGuestWeak(Model model) throws IOException {
        return "weakup/guest";
    }

    @RequestMapping(params = "method=contract", method = RequestMethod.GET)
    public String showContractWeak(Model model) throws IOException {
        return "weakup/contract";
    }

    @RequestMapping(params = "method=finance", method = RequestMethod.GET)
    public String showFinanceWeak(Model model) throws IOException {
        return "weakup/finance";
    }

    @RequestMapping(params = "method=defineSelfe", method = RequestMethod.GET)
    public String showDefineSelfWeak(Model model) throws IOException {
        return "weakup/defineSelfe";
    }
}
