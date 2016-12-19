package com.focustech.oss2008.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.service.ToolBoxService;
import com.focustech.oss2008.web.AbstractController;

@Controller
@RequestMapping("/area.do")
public class LoadAreaDataController extends AbstractController {
    @Autowired
    private ToolBoxService toolBoxService;

    @RequestMapping(params = "method=init")
    public void initData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String website = Constants.SITE_TYPE_MIC_CN;
        ajaxOutput(response, convert2JSONString(toolBoxService.getProvinceList(website)));
    }

    @RequestMapping(params = "method=next")
    public void next(String value, String type, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String website = Constants.SITE_TYPE_MIC_CN;
        ajaxOutput(response, convert2JSONString(toolBoxService.getNextAreaList(value, type, website)));
    }

    @RequestMapping(params = "method=initJob")
    public void initJob(String paramType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String website = Constants.SITE_TYPE_MIC_CN;
        ajaxOutput(response, convert2JSONString(toolBoxService.getJobProvinceList(paramType, website)));
    }
}
