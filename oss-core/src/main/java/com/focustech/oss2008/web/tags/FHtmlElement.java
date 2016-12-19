package com.focustech.oss2008.web.tags;

import java.util.List;

/**
 * HTML显示代码生成接口
 *
 * @author tc-hexuey
 */
public interface FHtmlElement {
    /**
     * 生成html字符串方法
     */
    String toHtml();

    void setName(String name);

    void setStyleId(String styleId);

    void setItems(List items);

    void setDefaultValue(Object defaultValue);

    void setOtherAttrs(String otherAttrs);

    void setFirstItemText(String firstItemText);

    void setFirstItemValue(String firstItemValue);

    void setItemLabel(String itemLabel);

    void setItemValue(String itemValue);

    void setExceptItems(Object items);

    void setWrap(boolean wrap);
}
