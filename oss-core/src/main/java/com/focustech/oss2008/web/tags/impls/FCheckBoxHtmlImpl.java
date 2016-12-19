package com.focustech.oss2008.web.tags.impls;

import java.util.List;

/**
 * @author tc-hexuey
 */
public class FCheckBoxHtmlImpl extends AbstractFHtmlElement {
    public String toHtml() {
        StringBuffer html = new StringBuffer();
        List items = getItems();
        // FKeyValue item= null;
        String label = "";
        String value = "";
        for (int i = 0; (items != null) && (i < items.size()); i++) {
            // item= (FKeyValue) items.get(i);
            label = getItemProperty(items.get(i), getItemLabel());
            value = getItemProperty(items.get(i), getItemValue());
            if (excepted(value)) {
                continue;
            }
            html.append("<label for=\"");
            html.append(getId() + "_" + value);
            html.append("\">");
            html.append("<input type=\"checkbox\" value=\"");
            html.append(value);
            html.append("\" name=\"");
            html.append(getName());
            html.append("\" id=\"");
            html.append(getId() + "_" + value);
            html.append("\" ");
            if (contains(value)) {
                html.append(" checked");
            }
            //
            html.append(" ");
            html.append(getOtherAttrs());
            html.append("/>");
            html.append(label);
            html.append("</label>");
            if (isWrap()) {
                html.append("<br/>");
            }
        }
        return html.toString();
    }
}
