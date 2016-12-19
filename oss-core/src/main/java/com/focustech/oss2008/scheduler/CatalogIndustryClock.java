package com.focustech.oss2008.scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.SystemConstants;
import com.focustech.oss2008.model.Category;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.EnvironmentParameter;
import com.focustech.oss2008.service.ToolBoxService;

public class CatalogIndustryClock extends AbstractServiceSupport {
    @Autowired
    private ToolBoxService toolBoxService;
    @Autowired
    private EnvironmentParameter environmentParameter;
    private String cookie;
    private List<String> lstsql = new ArrayList<String>();
    private String path = "";

    //
    public void createCatalogTmpFile() {
        log.error("行業分析生成中間表日終開始......");
        ceateTmpIndustyTab();
        log.error("行業分析生成中間表日終結束......");
        log.error("行業分析日終開始......");
        path = SystemConstants.getWebRootPath() + "Catalog_industry/";
        List<Category> lst = toolBoxService.getAllCatalogInfo(Constants.SITE_TYPE_MIC_EN);
        for (Category c : lst) {
            log.debug(c.getCatCode() + " in :" + System.currentTimeMillis());
            // newFile(c.getCatCode());
            // deleteFile(c.getCatCode());
            log.debug(c.getCatCode() + " out :" + System.currentTimeMillis());
        }
        log.error("行業分析日終結束......");
    }

    @SuppressWarnings("unchecked")
    private void ceateTmpIndustyTab() {

    }

    //
    private void newFile(long catCode) {
        InputStream in = null;
        BufferedReader br = null;
        Writer writer = null;
        try {
            // 取得需要訪問的URL鏈接
            StringBuffer bf =
                    new StringBuffer(environmentParameter.getStringValue("SAL")
                            + "industryInfo.do?method=queryCategory&searchword=" + catCode);
            //
            URL url = new URL(bf.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestProperty("contentType", "text/html; charset=BIG5");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, "GBK"));
                String tmp = null;
                writer = new FileWriter(path + catCode + "_tmp.html");
                writer.write("<base href='" + environmentParameter.getStringValue("SAL") + "'/> ");
                do {
                    tmp = br.readLine();
                    if (tmp != null) {
                        writer.write(tmp);
                        writer.write("\r\n");
                        writer.flush();
                    }
                }
                while (tmp != null);
            }
        }
        catch (Exception e) {
            log.error("出現異常 ︰ " + e.getMessage(), e);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    log.error("InputStream 關閉出現異常 ︰ " + e.getMessage(), e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    log.error("BufferedReader 關閉出現異常 ︰ " + e.getMessage(), e);
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (Exception e2) {
                    log.error("FileWriter 關閉出現異常 ︰ " + e2.getMessage(), e2);
                }
            }
        }
    }

    private void deleteFile(long catCode) {
        File f = null;
        try {
            f = new File(path + catCode + ".html");
            f.delete();
        }
        catch (Exception e) {
            log.error("刪除文件出錯 ︰" + e.getMessage(), e);
        }
        try {
            f = new File(path + catCode + "_tmp.html");
            f.renameTo(new File(path + catCode + ".html"));
        }
        catch (Exception e) {
            log.error("重命名文件出錯 ︰" + e.getMessage(), e);
        }
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
