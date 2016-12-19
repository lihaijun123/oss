package com.focustech.oss2008.web.tags.impls;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.focustech.oss2008.web.tags.FHtmlElement;

/**
 * html对象实现抽象类，通过此类来实现一些html对象的公共特性
 *
 * @author tc-hexuey
 */
public abstract class AbstractFHtmlElement implements FHtmlElement {
    private String name = "";
    private String styleId = "";
    private String type = "";
    private List items = null;
    //
    private Object defaultValue = null;
    private String otherAttrs = "";
    private String firstItemText = "";
    private String firstItemValue = "";
    //
    private String itemLabel = "";
    private String itemValue = "";
    private Object exceptItems = null;
    private boolean wrap = false;

    //
    protected boolean contains(String key) {
        if ((defaultValue == null) || (key == null)) {
            return false;
        }
        boolean selected = false;
        if (defaultValue instanceof Collection) {
            selected = ((Collection) defaultValue).contains(key);
        }
        else if (defaultValue instanceof Map) {
            selected = ((Map) defaultValue).containsKey(key);
        }
        else if (defaultValue instanceof String) {
            for (String item : String.valueOf(defaultValue).split("[,;]")) {
                selected = key.equals(item);
                if (selected) {
                    break;
                }
            }
        }
        if (!selected) {
            selected = key.equals(defaultValue.toString());
        }
        return selected;
    }

    protected boolean excepted(String key) {
        if ((exceptItems == null) || (key == null)) {
            return false;
        }
        boolean selected = false;
        if (exceptItems instanceof Collection) {
            selected = ((Collection) exceptItems).contains(key);
        }
        else if (exceptItems instanceof Map) {
            selected = ((Map) exceptItems).containsKey(key);
        }
        else if (exceptItems instanceof String) {
            for (String exceptItem : String.valueOf(exceptItems).split("[,;]")) {
                selected = key.equals(exceptItem);
                if (selected) {
                    break;
                }
            }
        }
        if (!selected) {
            selected = key.equals(exceptItems.toString());
        }
        return selected;
    }

    protected String getItemProperty(Object item, String property) {
        try {
            return BeanUtils.getProperty(item, property);
        }
        catch (Exception e) {
            return null;
        }
    }

    //
    public String getId() {
        if ((getStyleId() != null) && (getStyleId().length() > 0)) {
            return getStyleId();
        }
        return getName();
    }

    //
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
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

    /**
     * @return the styleId
     */
    public String getStyleId() {
        return styleId;
    }

    /**
     * @param styleId the styleId to set
     */
    public void setStyleId(String styleId) {
        this.styleId = styleId;
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
        if ((itemLabel == null) || (itemLabel.length() <= 0)) {
            return "text";
        }
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getItemValue() {
        if ((itemValue == null) || (itemValue.length() <= 0)) {
            return "value";
        }
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public void setExceptItems(Object items) {
        exceptItems = items;
    }

    public Object getExceptItems() {
        return exceptItems;
    }

    public boolean isWrap() {
        return wrap;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }
}
