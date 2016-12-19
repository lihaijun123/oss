package com.focustech.oss2008.web.tags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.oss2008.web.tags.impls.FCheckBoxHtmlImpl;
import com.focustech.oss2008.web.tags.impls.FRadioHtmlImpl;
import com.focustech.oss2008.web.tags.impls.FSelectHtmlImpl;

/**
 * 基于指定数据结构显示html页面元素的tag 主要功能是处理基于系统参数表的参数显示radio，checkbox，select等
 *
 * @author tc-hexuey
 */
public class FComplexHtmlElementTag extends AbstractHtmlInputElementTag {
    private static Map<String, String> IMPLS = new HashMap<String, String>();
    static {
        IMPLS.put("radio", FRadioHtmlImpl.class.getName());
        IMPLS.put("checkbox", FCheckBoxHtmlImpl.class.getName());
        IMPLS.put("select", FSelectHtmlImpl.class.getName());
    };
    private static final long serialVersionUID = 104421089243980131L;
    private String name = "";
    private String paramType = "";
    private String siteType = "2";
    private String type = "";
    private List items = null;
    private String firstItemText = "";
    private String firstItemValue = "";
    private String itemLabel = "";
    private String itemValue = "";
    //
    private Object defaultValue = null;
    private String otherAttrs = "";
    private Object exceptItems = null;
    private boolean wrap = false;

    //
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        WebApplicationContext content =
                (WebApplicationContext) pageContext.getServletContext().getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        if ((type == null) || (type.length() <= 0)) {
            type = "select";
        }
        FHtmlElement htmlEle = null;
        try {
            htmlEle = (FHtmlElement) Class.forName(IMPLS.get(getType())).newInstance();
            // htmlEle= (FHtmlElement) content.getBean(getType());;
            htmlEle.setName(name);
            htmlEle.setStyleId(getId());
            if ((paramType != null) && (paramType.length() > 0)) {
                OssAdminParameterService parametersService =
                        (OssAdminParameterService) content.getBean("ossAdminParameterServiceImpl");
                items = parametersService.listParameters(paramType, siteType);
                if (((itemLabel == null) || (itemLabel.length() <= 0))) {
                    if ((items != null) && (items.size() > 0) && (items.get(0) instanceof OssAdminParameter)) {
                        itemLabel = "parameterValue";
                        itemValue = "parameterKey";
                    }
                }
            }
            htmlEle.setItems(items);
            htmlEle.setDefaultValue(getDefaultValue());
            htmlEle.setOtherAttrs(otherAttrs);
            htmlEle.setFirstItemText(firstItemText);
            htmlEle.setFirstItemValue(firstItemValue);
            htmlEle.setItemLabel(itemLabel);
            htmlEle.setItemValue(itemValue);
            htmlEle.setExceptItems(exceptItems);
            htmlEle.setWrap(wrap);
            //
            pageContext.getOut().write(htmlEle.toHtml());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    //
    // ------------------------------------------------------------------------
    /**
     * @return the paramType
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * @param paramType the paramType to set
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * @return the siteType
     */
    public String getSiteType() {
        return siteType;
    }

    /**
     * @param siteType the siteType to set
     */
    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the items
     */
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return the defaultValue
     */
    public Object getDefaultValue() {
        if (defaultValue == null) {
            try {
                defaultValue = getDisplayString(getBoundValue(), getPropertyEditor());
            }
            catch (JspException e) {
            }
        }
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        super.setPath(name);
        this.name = name;
    }

    /**
     * @return the otherAttrs
     */
    public String getOtherAttrs() {
        return otherAttrs;
    }

    /**
     * @param otherAttrs the otherAttrs to set
     */
    public void setOtherAttrs(String otherAttrs) {
        this.otherAttrs = otherAttrs;
    }

    public String getFirstItemText() {
        return firstItemText;
    }

    public void setFirstItemText(String firstItemText) {
        this.firstItemText = firstItemText;
    }

    public String getFirstItemValue() {
        return firstItemValue;
    }

    public void setFirstItemValue(String firstItemValue) {
        this.firstItemValue = firstItemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public Object getExceptItems() {
        return exceptItems;
    }

    public void setExceptItems(Object exceptItems) {
        this.exceptItems = exceptItems;
    }

    public boolean isWrap() {
        return wrap;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }
}
