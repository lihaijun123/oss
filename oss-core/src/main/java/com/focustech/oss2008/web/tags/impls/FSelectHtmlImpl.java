package com.focustech.oss2008.web.tags.impls;

import java.util.List;

public class FSelectHtmlImpl extends AbstractFHtmlElement {
    public String toHtml() {
        StringBuffer html = new StringBuffer();
        List items = getItems();
        // FKeyValue item= null;
        String label = "";
        String value = "";
        html.append("<select name=\"");
        html.append(getName());
        html.append("\" id=\"");
        html.append(getId());
        html.append("\" ");
        html.append(getOtherAttrs());
        html.append(" >");
        if (getFirstItemText() != null && getFirstItemText().length() > 0) {
            html.append("<option value=\"");
            html.append(getFirstItemValue());
            html.append("\">");
            html.append(getFirstItemText());
            html.append("</option>");
        }
        //
        for (int i = 0; items != null && i < items.size(); i++) {
            // item= (FKeyValue) items.get(i);
            label = getItemProperty(items.get(i), getItemLabel());
            value = getItemProperty(items.get(i), getItemValue());
            if (excepted(value)) {
                continue;
            }
            html.append("<option value=\"");
            html.append(value);
            html.append("\" ");
            if (contains(value)) {
                html.append(" selected ");
            }
            html.append(" >");
            html.append(label);
            html.append("</option>");
        }
        html.append("</select>");
        return html.toString();
    }
}
