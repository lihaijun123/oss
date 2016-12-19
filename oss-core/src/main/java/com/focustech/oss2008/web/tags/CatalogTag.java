/*
 * Created on 2004-7-16 To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package com.focustech.oss2008.web.tags;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.oss2008.SystemConstants;
import com.focustech.oss2008.model.Catalog;
import com.focustech.oss2008.service.CatalogService;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-20
 */
public class CatalogTag extends AbstractHtmlInputElementTag {
    private static String SEP_ROLE = ",";
    private static String SEP_SEMICOLON = ";";
    //
    private String name = "";
    private String HTML_TYPE_TEXT = "text";
    private String defaultValues = "";
    private String maxLevel = "5";
    private String unLevels = "";
    private String single = "0";
    private String readonly = "false";
    private String selectonly = "false";
    private String event = "";
    private String hasLinkCatalog = "false";
    private String unLevelType = "";
    private String isFromCache = "true";
    private String quickSelect = "false";
    private String suggest = "";
    private String size = "";
    private String server = "";
    private String lanCode = "0";

    //
    @Override
    public void release() {
        super.release();
        HTML_TYPE_TEXT = "text";
        defaultValues = "";
        maxLevel = "5";
        unLevels = "";
        single = "0";
        readonly = "false";
        selectonly = "false";
        event = "";
        hasLinkCatalog = "false";
        unLevelType = "";
        isFromCache = "true";
        quickSelect = "false";
        suggest = "";
        lanCode = "0";
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    // public int doStartTag() throws JspException
    // {
    // return EVAL_BODY_INCLUDE;
    // }
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        try {
            pageContext.getOut().write(getHtml());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            // getLogger().error("Error ��ñ���e������g�JJSP��X�y!", ioe);
        }
        return EVAL_PAGE;
    }

    private String getHtml() throws JspException {
        StringBuffer bf = new StringBuffer();
        //
        if ("true".equals(getSuggest())) {
            bf.append("<script>");
            bf.append("var _sfwcss = document.getElementById('SFWCSS');");
            bf.append("var _sfwjs = document.getElementById('SFWJS');");
            bf.append("var _head = document.getElementsByTagName('head').item(0);");
            bf.append("if(!_sfwcss){");
            bf.append("_script=document.createElement('script');");
            bf.append("_script.src='/js/suggest.js';");
            bf.append("_script.type='text/javascript';");
            bf.append("_script.id='SFWJS';");
            bf.append("_head.appendChild(_script);");
            bf.append("}");
            bf.append("if(!_sfwcss){");
            bf.append("_css=document.createElement('link');");
            bf.append("_css.href='/style/suggest.css';");
            bf.append("_css.rel='stylesheet';");
            bf.append("_css.type='text/css';");
            bf.append("_css.id='SFWCSS';");
            bf.append("_head.appendChild(_css);");
            bf.append("}");
            bf.append("if(document.addEventListener)" + "{window.addEventListener(\"load\",_initializeSFW,false);}"
                    + "else{window.attachEvent(\"onload\",_initializeSFW);}");
            bf.append("function _initializeSFW(){initializeSuggestFramework();}");
            bf.append("</script>");
        }
        bf.append("<table><tr><td>");
        bf.append("<input type=\"");
        bf.append(HTML_TYPE_TEXT);
        bf.append("\" name=\"");
        bf.append(getName());
        bf.append("\" id=\"");
        bf.append(getId());
        bf.append("\" value=\"");
        bf.append(getDefaultValues());
        bf.append("\" size=\"");
        bf.append(getSize());
        bf.append("\" ");
        if ("true".equals(getSuggest())) {
            bf.append("heading=\"true\" action=\"/productcatalog.ajax\" ");
            bf.append(" actionargs=\"action=autoComplete\" delay=\"1000\" columns=\"2\"");
        }
        if ("true".equals(readonly) || "true".equals(selectonly)) {
            bf.append("readonly=true ");
        }
        bf.append(" " + getEvent() + "/>");
        bf.append("</td><td>");
        // bf.append("<table style='float:left'><tr><td>");
        if ("false".equals(readonly)) {
            // bf.append("<a href=\"javascript:openCatalogWindow('" + getServer() + "catalog.do?tagName=");
            bf.append("<a href=\"javascript:openCatalogWindow('catalog.do?tagName=");
            bf.append(getId());
            bf.append("&lanCode=");
            bf.append(getLanCode());
            bf.append("&maxLevel=");
            bf.append(getMaxLevel());
            bf.append("&unLevels=");
            bf.append(getUnLevels());
            bf.append("&isFromCache=");
            bf.append(getIsFromCache());
            bf.append("&single=");
            bf.append(getSingle());
            bf.append("&quickSelect=");
            bf.append(getQuickSelect());
            bf.append("&hasLinkCatalog=");
            bf.append(getHasLinkCatalog());
            bf.append("&unLevelType=");
            bf.append(getUnLevelType());
            bf.append("&selectedCatCode=");
            bf.append("',document.getElementsByName('");
            bf.append(getName());
            bf.append("')[0].value");
            if (!getServer().startsWith(pageContext.getRequest().getServerName())) {
                // bf.append("<script>document.domain = \"");
                bf.append(",'");
                bf.append(SystemConstants.getEnvironmentParameter("DOMAIN", pageContext.getServletContext()));
                bf.append("'");
                // bf.append("\";</script>");
            }
            bf.append(")\" id='" + getId() + "CatLink'>");
            bf.append("选择分类</a>\n");
        }
        bf.append("</td></tr><tr><td colspan='2'>");
        bf.append("<span id=\"divViewCatName_" + getId() + "\">" + getCatName() + "</span>");
        bf.append("</td></tr></table>");
        return bf.toString();
    }

    private String getCatName() {
        String strRe = "";
        StringTokenizer st = new StringTokenizer(getDefaultValues(), SEP_ROLE + SEP_SEMICOLON);
        if ((st != null) && st.hasMoreTokens()) {
            WebApplicationContext content =
                    (WebApplicationContext) pageContext.getServletContext().getAttribute(
                            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
            CatalogService catalogTool = (CatalogService) content.getBean("catalogTool");
            while (st.hasMoreTokens()) {
                try {
                    String catCode = st.nextToken();
                    Catalog catalog = catalogTool.getCatalogInfo(lanCode, catCode);
                    String catNameEN = catalog.getCatNameEn();// catalogTool.getCatalogName(catCode,
                    // Constants.PARAMETER_LANCOD_en_US);
                    String catNameCN = catalog.getCatNameCn();// catalogTool.getCatalogName(catCode,
                    // Constants.PARAMETER_LANCOD_zh_CN);
                    strRe += catNameCN + "/" + catNameEN + ",";
                }
                catch (Exception e) {
                }
            }
        }
        if (strRe.endsWith(",")) {
            strRe = strRe.substring(0, strRe.length() - 1);
        }
        if ("/".equals(strRe)) {
            strRe = "";
        }
        return strRe;
    }

    public String getId() {
        if ((super.getId() == null) || (super.getId().length() <= 0)) {
            return getName();
        }
        return super.getId();
    }

    /**
     * @return
     */
    public String getMaxLevel() {
        return maxLevel;
    }

    /**
     * @return
     */
    public String getSingle() {
        return single;
    }

    /**
     * @return
     */
    public String getUnLevels() {
        return unLevels;
    }

    /**
     * @param string
     */
    public void setMaxLevel(String string) {
        maxLevel = string;
    }

    /**
     * @param string
     */
    public void setSingle(String string) {
        single = string;
    }

    /**
     * @param string
     */
    public void setUnLevels(String string) {
        unLevels = string;
    }

    /**
     * @return
     */
    public String getDefaultValues() {
        if (defaultValues == null) {
            return "";
        }
        return defaultValues;
    }

    /**
     * @param string
     */
    public void setDefaultValues(String string) {
        defaultValues = string;
    }

    /**
     * @return
     */
    public String getReadonly() {
        return readonly;
    }

    /**
     * @param string
     */
    public void setReadonly(String string) {
        readonly = string;
    }

    /**
     * @return
     */
    public String getEvent() {
        return event;
    }

    /**
     * @return
     */
    public String getSelectonly() {
        return selectonly;
    }

    /**
     * @param string
     */
    public void setEvent(String string) {
        event = string;
    }

    /**
     * @param string
     */
    public void setSelectonly(String string) {
        selectonly = string;
    }

    /**
     * @return
     */
    public String getHasLinkCatalog() {
        return hasLinkCatalog;
    }

    /**
     * @return
     */
    public String getUnLevelType() {
        return unLevelType;
    }

    /**
     * @param string
     */
    public void setHasLinkCatalog(String string) {
        hasLinkCatalog = string;
    }

    /**
     * @param string
     */
    public void setUnLevelType(String string) {
        unLevelType = string;
    }

    /**
     * @return
     */
    public String getIsFromCache() {
        return isFromCache;
    }

    /**
     * @param string
     */
    public void setIsFromCache(String string) {
        isFromCache = string;
    }

    /**
     * @return
     */
    public String getQuickSelect() {
        return quickSelect;
    }

    /**
     * @param string
     */
    public void setQuickSelect(String string) {
        quickSelect = string;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getServer() {
        if (server == null) {
            server = "";
        }
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

}
