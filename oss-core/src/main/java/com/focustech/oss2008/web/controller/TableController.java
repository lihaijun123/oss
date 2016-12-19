package com.focustech.oss2008.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.oss2008.web.AbstractController;
import com.focustech.table.engine.impl.DefaultRequestAnalyseEngine;
import com.focustech.table.web.ext.extcomp.TableParameters;

/**
 * <li></li>
 *
 * @author yangpeng 2008-5-5 上午10:11:35
 */
@RequestMapping("/getTable.do")
public class TableController extends AbstractController {
    @Autowired
    private DefaultRequestAnalyseEngine tableService;

    @RequestMapping(params = "method=data", method = RequestMethod.POST)
    public String getTableData(TableParameters tableParameters, HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        request.getSession().setAttribute("sessionName", "michael");
        String tableName = tableParameters.getTableName();
        response.setContentType("text/html; charset=BIG5");
        String str2 = tableService.getData(tableParameters, request);
        response.getWriter().write(str2);
        response.getWriter().flush();
        return null;
    }

    @RequestMapping(params = "method=view", method = RequestMethod.GET)
    public String getTable(TableParameters tableParameters, Model model) {
        String tablHtml = tableService.getTable(tableParameters);
        model.addAttribute("table_html", tablHtml);
        return "/table/view";
    }

    @RequestMapping(params = "method=kk", method = RequestMethod.POST)
    public String getTable(String name[], HttpServletResponse response) {
        String str2 = "請選擇記錄!";
        if (name != null) {
            String a = name[0];
            str2 = a;
        }
        try {
            response.setContentType("text/html; charset=BIG5");
            response.getWriter().write(str2);
            response.getWriter().flush();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
