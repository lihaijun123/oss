package com.focustech.oss2008.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.oss2008.web.AbstractController;

/**
 * Copyright (c) 2008, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-9-19 下午02:32:35
 */
@Controller
public class CatalogController extends AbstractController {
    @Autowired
    EnvironmentParameter environmentParameterImpl;

    /**
     * 獲取產品標簽樹
     */
    @RequestMapping("/catalog.do")
    public String next(HttpServletRequest request, HttpServletResponse response) {
        InputStream in = null;
        BufferedReader br = null;
        try {
            response.setCharacterEncoding("GBK");
            String tagName = StringUtils.notNull(request.getParameter("tagName"));
            String lanCode = StringUtils.notNull(request.getParameter("lanCode"));
            String maxLevel = StringUtils.notNull(request.getParameter("maxLevel"));
            String unLevels = StringUtils.notNull(request.getParameter("unLevels"));
            String isFromCache = StringUtils.notNull(request.getParameter("isFromCache"));
            String single = StringUtils.notNull(request.getParameter("single"));
            String quickSelect = StringUtils.notNull(request.getParameter("quickSelect"));
            String hasLinkCatalog = StringUtils.notNull(request.getParameter("hasLinkCatalog"));
            String unLevelType = StringUtils.notNull(request.getParameter("unLevelType"));
            String selectedCatCode = StringUtils.notNull(request.getParameter("selectedCatCode"));
            String selectedCatCodeName = StringUtils.notNull(request.getParameter("selectedCatCodeName"));
            String currLevel = StringUtils.notNull(request.getParameter("currLevel"));
            String catCode[] = request.getParameterValues("catCode");
            String catCode_[] = request.getParameterValues("catCode_");
            String clickCatCode = StringUtils.notNull(request.getParameter("clickCatCode"));
            String action = StringUtils.notNull(request.getParameter("action"));
            //
            StringBuffer bf = new StringBuffer(environmentParameterImpl.getStringValue("EDT") + "catalog.do?action=");
            bf.append(action);
            bf.append("&selectedCatCodeName=");
            bf.append(URLEncoder.encode(selectedCatCodeName, "GBK"));
            bf.append("&currLevel=");
            bf.append(currLevel);
            for (int i = 0; (catCode != null) && (i < catCode.length); i++) {
                bf.append("&catCode=");
                bf.append(catCode[i]);
            }
            for (int i = 0; (catCode_ != null) && (i < catCode_.length); i++) {
                bf.append("&catCode_=");
                bf.append(catCode_[i]);
            }
            bf.append("&clickCatCode=");
            bf.append(clickCatCode);
            bf.append("&tagName=");
            bf.append(tagName);
            bf.append("&lanCode=");
            bf.append(lanCode);
            bf.append("&maxLevel=");
            bf.append(maxLevel);
            bf.append("&unLevels=");
            bf.append(unLevels);
            bf.append("&isFromCache=");
            bf.append(isFromCache);
            bf.append("&single=");
            bf.append(single);
            bf.append("&quickSelect=");
            bf.append(quickSelect);
            bf.append("&hasLinkCatalog=");
            bf.append(hasLinkCatalog);
            bf.append("&unLevelType=");
            bf.append(unLevelType);
            bf.append("&selectedCatCode=");
            bf.append(selectedCatCode);
            //
            String cookie = "";
            Cookie[] cs = request.getCookies();
            for (int i = 0; (cs != null) && (i < cs.length); i++) {
                cookie += cs[i].getName() + "=" + cs[i].getValue();
                cookie += ";";
            }
            //
            URL url = new URL(bf.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestProperty("contentType", "text/html; charset=BIG5");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, "GBK"));
                String tmp = null;
                response.setContentType("text/html; charset=BIG5");
                do {
                    tmp = br.readLine();
                    if (tmp != null) {
                        response.getWriter().write(tmp);
                        response.getWriter().write("\r\n");
                        response.flushBuffer();
                    }
                }
                while (tmp != null);
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 獲取產品標簽樹
     */
    @RequestMapping("/relatedlink.do")
    public String relatedLink(HttpServletRequest request, HttpServletResponse response) {
        InputStream in = null;
        BufferedReader br = null;
        try {
            response.setCharacterEncoding("GBK");
            String returnCatCode = StringUtils.notNull(request.getParameter("returnCatCode"));
            String returnCatName = StringUtils.notNull(request.getParameter("returnCatName"));
            String lanCode = StringUtils.notNull(request.getParameter("lanCode"));
            String selectValue = StringUtils.notNull(request.getParameter("selectValue"));
            String action = StringUtils.notNull(request.getParameter("action"));
            //
            StringBuffer bf =
                    new StringBuffer(environmentParameterImpl.getStringValue("EDT") + "relatedlink.do?action=");
            bf.append(action);
            bf.append("&returnCatCode=");
            bf.append(returnCatCode);
            bf.append("&returnCatName=");
            bf.append(returnCatName);
            bf.append("&lanCode=");
            bf.append(lanCode);
            bf.append("&selectValue=");
            bf.append(selectValue);
            //
            String cookie = "";
            Cookie[] cs = request.getCookies();
            for (int i = 0; (cs != null) && (i < cs.length); i++) {
                cookie += cs[i].getName() + "=" + cs[i].getValue();
                cookie += ";";
            }
            //
            URL url = new URL(bf.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestProperty("contentType", "text/html; charset=BIG5");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, "GBK"));
                String tmp = null;
                response.setContentType("text/html; charset=BIG5");
                do {
                    tmp = br.readLine();
                    if (tmp != null) {
                        response.getWriter().write(tmp);
                        response.getWriter().write("\r\n");
                        response.flushBuffer();
                    }
                }
                while (tmp != null);
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
