package com.focustech.oss2008.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.service.OssAdminParameterService;

/**
 * 從參數什轉到參數文字
 *
 * @author tc-hexuey
 */
public class SystemParamTag extends AbstractHtmlInputElementTag {
    private static final long serialVersionUID = 104421089243980131L;
    private String paramKey = "";
    private String paramType = "";
    private String siteType = "";

    //
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        try {
            WebApplicationContext content =
                    (WebApplicationContext) pageContext.getServletContext().getAttribute(
                            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
            OssAdminParameterService parametersService =
                    (OssAdminParameterService) content.getBean("ossAdminParameterServiceImpl");
            if (siteType == null || siteType.length() <= 0) {
                siteType = Constants.SITE_TYPE_MIC_CN;
            }
            OssAdminParameter paramter =
                    parametersService.selectParameterByTypeKeyAndSite(paramType, paramKey, siteType);
            if (paramter != null) {
                pageContext.getOut().write(paramter.getParameterValue());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }
}
